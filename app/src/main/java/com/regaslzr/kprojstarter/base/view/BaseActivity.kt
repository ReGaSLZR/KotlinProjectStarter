package com.regaslzr.kprojstarter.base.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.regaslzr.kprojstarter.base.view.screenmanager.ScreenManager
import com.regaslzr.kprojstarter.common.extensions.loadFragment
import com.regaslzr.kprojstarter.common.extensions.logDebug
import org.koin.android.ext.android.inject

/*
* This class is to be the superclass of any Activity in the application.
*
* This class is configured to already observe and react:
* [1] when the ScreenManager.currentScreen has changed (thus, loads that new screen)
* [2] when the ScreenManager.backstack has been cleared (thus, finish() the Activity)
* */
abstract class BaseActivity : AppCompatActivity() {

    /*
    * Member variable "screenManager" is ready for use for any Dispatcher calls (dispatch/redirect, goBack)
    * and for screen checking functions and backStack observation
    * */
    val screenManager : ScreenManager by inject()

    /*
    * Function getChangeableContentId() is where the child class can specify
    * the ID from the layout that is changeable / where the BaseView children will be displayed
    * */
    protected abstract fun getChangeableContentId(): Int

    /*
    * Function getHomeScreen() is where the child class can specify which
    * descendant of BaseView / fullscreen page is to be:
    * [1] displayed first, and
    * [2] set as home (will clear the backstack of the Activity when visited)
    * */
    protected abstract fun getHomeScreen(): BaseView

    protected abstract fun onChangeScreen()
    protected abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenManager.setHomeScreen(getHomeScreen())
        setLayoutResource()
        setObservers()
    }

    override fun onBackPressed() {
        screenManager.goBack()
    }

    private fun setLayoutResource() {
        if(getLayoutResource() == 0) {
            logDebug(TAG, "Layout Resource cannot be 0")
        }
        else {
            setContentView(getLayoutResource())
        }
    }

    private fun setObservers() {
        screenManager.currentScreen.observe(this, Observer {
            logDebug(TAG, "currentScreen changed...")

            it?.let {
                logDebug(
                    TAG,
                    "currentScreen changed with new value: ${it::class.java.simpleName}"
                )
                loadFragment(getChangeableContentId(), it)
                onChangeScreen()
            }
        })

        screenManager.isClear.observe(this, Observer {
            if(it == true) {
                finish()
            }
        })
    }

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }

}