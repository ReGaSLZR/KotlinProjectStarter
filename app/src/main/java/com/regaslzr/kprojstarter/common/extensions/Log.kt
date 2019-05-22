package com.regaslzr.kprojstarter.common.extensions

import android.util.Log
import com.regaslzr.kprojstarter.BuildConfig


fun logDebug(tag: String = "", message: String) {
    if(BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun logWarning(tag: String = "", message: String) {
    if(BuildConfig.DEBUG) {
        Log.w(tag, message)
    }
}

fun logError(tag: String = "", message: String) {
    if(BuildConfig.DEBUG) {
        Log.e(tag, message)
    }
}