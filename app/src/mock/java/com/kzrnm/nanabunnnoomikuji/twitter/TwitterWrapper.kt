package com.kzrnm.nanabunnnoomikuji.twitter

import androidx.lifecycle.MutableLiveData
import twitter4j.Status
import java.time.Instant
import java.util.*

@Suppress("RedundantNullableReturnType")
object TwitterWrapper {
    val info = MutableLiveData<LoginInfo?>()

    private var request: Any? = null

    val runningAuthorization
        get() = request != null

    suspend fun getAuthorizationURL(): String? {
        request = Any()
        return null
    }

    suspend fun auth(pin: String): LoginInfo? {
        if (request == null) {
            return null
        }
        request = null

        val result = LoginInfo("mock", "mock", "mock")
        info.postValue(result)
        return result
    }

    suspend fun verify(token: String, secret: String) {
        info.postValue(LoginInfo("mock", token, secret))
    }

    suspend fun tweet(statusText: String, inReplyTo: String? = null): Status? {
        if (info.value != null) {
            val inReplyToId = inReplyTo?.let {
                try {
                    return@let it.toLong()
                } catch (e: NumberFormatException) {
                }

                return@let Regex("""/(\d+)[^/]*""").find(it)?.groupValues?.get(1)
                    ?.toLongOrNull()
            }
            return MockStatus(
                1428325883807510533,
                Date.from(
                    Instant.now()
                ),
                MockUser("naminodarie")
            )
        }
        return null
    }

}

