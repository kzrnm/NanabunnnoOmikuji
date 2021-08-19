package com.kzrnm.nanabunnnoomikuji.twitter

import androidx.lifecycle.MutableLiveData
import com.kzrnm.nanabunnnoomikuji.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.Status
import twitter4j.StatusUpdate
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import java.util.*


@Suppress("RedundantNullableReturnType")
object TwitterWrapper {
    private val twitter = TwitterFactory.getSingleton()
    val info = MutableLiveData<LoginInfo?>()

    private var request: RequestToken? = null

    val runningAuthorization
        get() = request != null

    init {
        twitter.setOAuthConsumer(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)
    }

    suspend fun getAuthorizationURL(): String? =
        withContext(Dispatchers.IO) {
            try {
                request = twitter.oAuthRequestToken
                request?.authorizationURL
            } catch (e: TwitterException) {
                null
            }
        }

    suspend fun auth(pin: String): LoginInfo? {
        if (request == null) {
            return null
        }
        val accessToken =
            withContext(Dispatchers.IO) {
                try {
                    twitter.getOAuthAccessToken(request, pin)
                } catch (e: TwitterException) {
                    null
                }
            }
        request = null

        val result = accessToken?.let {
            LoginInfo(it.screenName, it.token, it.tokenSecret)
        }
        info.postValue(result)
        return result
    }

    suspend fun verify(token: String, secret: String) {
        val accessToken = AccessToken(token, secret)
        twitter.oAuthAccessToken = accessToken

        val user =
            withContext(Dispatchers.IO) {
                try {
                    twitter.verifyCredentials()
                } catch (e: TwitterException) {
                    null
                }
            } ?: return
        info.postValue(LoginInfo(user.screenName, token, secret))
    }

    suspend fun tweet(statusText: String, inReplyTo: String? = null): Status? {
        if (info.value == null) return null

        val inReplyToId = inReplyTo?.let {
            try {
                return@let it.toLong()
            } catch (e: NumberFormatException) {
            }

            return@let Regex("""/(\d+)[^/]*""").find(it)?.groupValues?.get(1)
                ?.toLongOrNull()
        }
        return withContext(Dispatchers.IO) {
            try {
                val u = StatusUpdate(statusText)
                if (inReplyToId != null)
                    u.inReplyToStatusId = inReplyToId
                twitter.updateStatus(u)
            } catch (e: TwitterException) {
                null
            }
        }
    }

}