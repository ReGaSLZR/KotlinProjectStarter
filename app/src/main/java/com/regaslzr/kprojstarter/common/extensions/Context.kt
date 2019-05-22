package com.regaslzr.kprojstarter.common.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String, isDurationShort: Boolean = true) {
    Toast.makeText(this, message,
        if(isDurationShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
        .show()
}