package com.regaslzr.kprojstarter.common.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.loadFragment(layout: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(layout, fragment)
        .commit()
}