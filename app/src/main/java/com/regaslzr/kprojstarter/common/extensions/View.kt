package com.regaslzr.kprojstarter.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    if(layoutId == 0) {
        throw RuntimeException("ViewGroup.inflate: Invalid Layout ID")
    }

    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}