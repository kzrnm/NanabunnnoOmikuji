package com.kzrnm.nanabunnnoomikuji.ui

import android.widget.Toast
import com.hadilq.liveevent.LiveEvent

data class ToastMessage(val message: String, val isShort: Boolean = true) {
    val toastLength = if (isShort) {
        Toast.LENGTH_SHORT
    } else {
        Toast.LENGTH_LONG
    }
}

class ToastMessageEvent : LiveEvent<ToastMessage>()