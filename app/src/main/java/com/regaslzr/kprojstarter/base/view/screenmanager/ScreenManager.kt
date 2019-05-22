package com.regaslzr.kprojstarter.base.view.screenmanager

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import com.regaslzr.kprojstarter.base.view.BaseView
import com.regaslzr.kprojstarter.common.extensions.logDebug
import com.regaslzr.kprojstarter.common.extensions.logWarning
import java.util.*

/*
* This is the class that is injected to the BaseActivity.
* As it implements the Dispatcher interface, it has the same power to
* load/redirect to a new screen, and go back to the previous one.
*
* But this class also has the responsibility to
* manage the backStack, and keep track of the homeScreen (for backStack clearing).
* In that regard, this class offers the observation of currentScreen and isClear live data -- perfect for
* the BaseActivity (where this should be injected only) to effectively manage what's on the device's screen.
* */
class ScreenManager : Dispatcher {

    val currentScreen = MutableLiveData<BaseView>()
    val isClear = MutableLiveData<Boolean>()

    private var homeScreenClass: String = ""
    private val backStack = Stack<BaseView>()

    override fun dispatch(screen: BaseView) {
        logDebug(TAG, "dispatch() called. Screen is ${screen.javaClass.simpleName}")

        if(currentScreen.javaClass.simpleName.equals(screen.javaClass.simpleName)) {
            logWarning(TAG, "dispatch() ignored. " +
                    "The screen on top of backstack is already the desired destination.")
            return
        }
        else {
            logDebug(TAG, "dispatch() on progress...")

            if (!backStack.empty() && isHomeScreen(screen)) {
                logWarning(TAG, "dispatch() backStack cleared...")
                backStack.clear()
            }

            backStack.push(screen)
            currentScreen.value = screen
        }
    }

    override fun goBack() {
        logDebug(TAG, "goBack() called")
        backStack.pop()

        if(backStack.isEmpty()) {
            logDebug(TAG, "backStack is now empty")
            isClear.value = true
        }
        else {
            currentScreen.value = backStack.lastElement()
        }
    }

    private fun isHomeScreen(screen: Fragment): Boolean =
        (screen::class.java.simpleName.equals(homeScreenClass))

    fun setHomeScreen(homeScreen: BaseView) {
        logDebug(TAG, "setHomeScreen() called...")
        homeScreenClass = homeScreen::class.java.simpleName
        currentScreen.value = homeScreen

        backStack.push(homeScreen)
    }

    fun isCurrentScreenHome(): Boolean {
        currentScreen.value?.let {
            return isHomeScreen(it)
        }

        return false
    }

    companion object {
        private val TAG = ScreenManager::class.java.simpleName
    }

}