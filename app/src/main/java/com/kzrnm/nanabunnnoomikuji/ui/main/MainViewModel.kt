package com.kzrnm.nanabunnnoomikuji.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.kzrnm.nanabunnnoomikuji.R
import com.kzrnm.nanabunnnoomikuji.preferences.SharedPreferencesWrapper
import com.kzrnm.nanabunnnoomikuji.twitter.TwitterWrapper
import com.kzrnm.nanabunnnoomikuji.twitter.url
import kotlinx.coroutines.launch
import twitter4j.Status
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainViewModel(private val app: Application) : AndroidViewModel(app) {
    val pref = SharedPreferencesWrapper(app)

    val loginUserName = TwitterWrapper.info.map { it?.screenName }
    val isLoggedIn = TwitterWrapper.info.map { it != null }

    val omikujiTypeId = MutableLiveData(R.id.radio_omikuji_daikichi)
    private val omikujiType = omikujiTypeId.map {
        when (it) {
            R.id.radio_omikuji_daikichi -> "大"
            R.id.radio_omikuji_chukichi -> "中"
            R.id.radio_omikuji_shokichi -> "小"
            else -> ""
        }
    }
    val omikujiMemberId = MutableLiveData(R.id.radio_member_amaki)
    private val omikujiMember = omikujiMemberId.map {
        when (it) {
            R.id.radio_member_amaki -> "サリー"
            R.id.radio_member_umino -> "るり"
            R.id.radio_member_kawase -> "うた"
            R.id.radio_member_kuraoka -> "岡"
            R.id.radio_member_saijo -> "なご"
            R.id.radio_member_shirosawa -> "かな"
            R.id.radio_member_suzuhana -> "もえ"
            R.id.radio_member_takatsuji -> "うらら"
            R.id.radio_member_takeda -> "なっち"
            R.id.radio_member_miyase -> "れい"
            else -> ""
        }
    }

    val tweetText = MediatorLiveData<String>()

    private fun updatePreviousTweet(status: Status) {
        pref.savePreviousStatus(status)
        previousTweetTime.postValue(status.createdAt.toInstant())
        previousTweetUrl.postValue(status.url)
    }

    val previousTweetUrl = MutableLiveData(pref.lastTweetUrl)

    private val previousTweetTime = MutableLiveData(
        Instant.ofEpochMilli(pref.lastTweetTime)
    )
    val previousTweetTimeText =
        previousTweetTime.map {
            if (it <= Instant.EPOCH) ""
            else LocalDateTime.ofInstant(it, ZoneId.systemDefault())
                .format(SimpleDateTimeFormatter)
        }

    val errorMessage = MutableLiveData<String?>()

    init {
        val omikujiObserver = Observer<String> {
            val type = omikujiType.value ?: ""
            val member = omikujiMember.value ?: ""
            tweetText.postValue("$type$member")
        }
        tweetText.addSource(omikujiType, omikujiObserver)
        tweetText.addSource(omikujiMember, omikujiObserver)
    }

    fun verify() = viewModelScope.launch {
        val token = pref.userToken
        val secret = pref.userSecret

        if (token != null && secret != null) {
            TwitterWrapper.verify(token, secret)
        }
    }

    fun logout() {
        pref.clearUser()
        TwitterWrapper.info.postValue(null)
    }

    fun tweet() = viewModelScope.launch {
        val tweetText = this@MainViewModel.tweetText.value
        if (tweetText.isNullOrBlank()) {
            errorMessage.postValue(app.getString(R.string.error_empty_tweet))
            return@launch
        }
        val status = TwitterWrapper.tweet(tweetText, previousTweetUrl.value)
        if (status == null || status.id < 0) {
            errorMessage.postValue(app.getString(R.string.error_failed_to_tweet))
            return@launch
        }
        updatePreviousTweet(status)
    }

    companion object {
        val SimpleDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")!!
    }
}