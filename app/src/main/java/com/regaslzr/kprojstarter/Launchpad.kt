package com.regaslzr.kprojstarter

import android.app.Application
import com.regaslzr.kprojstarter.injection.prepareInjections

class Launchpad : Application() {

    override fun onCreate() {
        super.onCreate()
        prepareInjections()
    }

}