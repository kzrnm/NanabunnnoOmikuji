package com.kzrnm.nanabunnnoomikuji.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.kzrnm.nanabunnnoomikuji.twitter.LoginInfo
import com.kzrnm.nanabunnnoomikuji.twitter.url
import twitter4j.Status

class SharedPreferencesWrapper(context: Context) {
    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val encryptedPreferences: SharedPreferences

    init {
        val mainKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        encryptedPreferences = EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_PREF_NAME,
            mainKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    val userName: String?
        get() = encryptedPreferences.getString(USER_NAME, null)
    val userToken: String?
        get() = encryptedPreferences.getString(USER_TOKEN, null)
    val userSecret: String?
        get() = encryptedPreferences.getString(USER_SECRET, null)

    val lastTweetTime: Long
        get() = preferences.getLong(LAST_TWEET_TIME, -1)

    val lastTweetUrl: String?
        get() = preferences.getString(LAST_TWEET_URL, null)

    fun savePreviousStatus(status: Status) {
        preferences.edit()
            .putLong(LAST_TWEET_TIME, status.createdAt.toInstant().toEpochMilli())
            .putString(LAST_TWEET_URL, status.url)
            .apply()
    }

    fun saveUser(loginInfo: LoginInfo) {
        encryptedPreferences.edit()
            .putString(USER_NAME, loginInfo.screenName)
            .putString(USER_TOKEN, loginInfo.token)
            .putString(USER_SECRET, loginInfo.secret)
            .apply()
    }

    fun clearUser() {
        encryptedPreferences.edit()
            .remove(USER_NAME)
            .remove(USER_TOKEN)
            .remove(USER_SECRET)
            .apply()
    }

    companion object {
        const val ENCRYPTED_PREF_NAME = "EncryptedPref"
        const val LAST_TWEET_URL = "lastTweetUrl"
        const val LAST_TWEET_TIME = "lastTweetTime"
        const val USER_NAME = "userName"
        const val USER_TOKEN = "userToken"
        const val USER_SECRET = "userSecret"
    }
}