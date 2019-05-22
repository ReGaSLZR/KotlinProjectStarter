package com.regaslzr.kprojstarter.base.view.screenmanager

import com.regaslzr.kprojstarter.base.view.BaseView

/*
* This is the interface that is injected into the BaseView
* to give BaseView children classes the power to signal to their host Activity to
* load/redirect to a new screen, and go back to the previous screen (and close the current one)
* */
interface Dispatcher {
    fun dispatch(screen: BaseView) //load/redirect to a new BaseView/screen
    fun goBack()
}