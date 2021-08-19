package com.kzrnm.nanabunnnoomikuji.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kzrnm.nanabunnnoomikuji.preferences.SharedPreferencesWrapper
import com.kzrnm.nanabunnnoomikuji.twitter.LoginInfo
import com.kzrnm.nanabunnnoomikuji.twitter.TwitterWrapper
import kotlinx.coroutines.launch

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val pref = SharedPreferencesWrapper(app)
    val pin = MutableLiveData<String?>()

    val authorizationURL = MutableLiveData<String?>(null)
    val authResult = MutableLiveData<LoginInfo?>()

    fun getAuthorizationURL() = viewModelScope.launch {
        authorizationURL.postValue(TwitterWrapper.getAuthorizationURL())
    }

    fun auth() = viewModelScope.launch {
        val pin = this@LoginViewModel.pin.value
        if (pin.isNullOrEmpty() || !TwitterWrapper.runningAuthorization) {
            authResult.postValue(null)
            return@launch
        }
        val userInfo = TwitterWrapper.auth(pin)
        if (userInfo != null) {
            pref.saveUser(userInfo)
        }
        authResult.postValue(userInfo)
    }
}