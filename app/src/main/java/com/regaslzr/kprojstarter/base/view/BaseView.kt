package com.regaslzr.kprojstarter.base.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.extensions.inflate
import com.regaslzr.kprojstarter.common.toolbar.ToolbarSetting
import com.regaslzr.kprojstarter.common.dialog.LoadingDialog
import com.regaslzr.kprojstarter.common.dialog.MessageDialog
import com.regaslzr.kprojstarter.base.view.screenmanager.Dispatcher
import com.regaslzr.kprojstarter.common.extensions.logDebug
import org.koin.android.ext.android.inject

/*
* This class is to be the superclass of all the "Views" in the application.
*
* A "View" represents a fullscreen page that contains and manipulates
*   the UI elements which in turn, represent a feature.
* A "View" may or may not have a partner "ViewModel" (see BaseViewModel class)
*   for business UseCase executions, and disposables release.
*
* Example children views:
*   - AboutView (no partner ViewModel)
*   - GenderizeNameView (uses both MessageDialog and LoadingDialog, has a partner ViewModel)
*   - RandomJokeView (uses LoadingDialog, has a partner ViewModel)
*
* */
abstract class BaseView : Fragment() {

    /*
    * The "dispatcher" member variable is an interface ready to
    * signal to the host Activity any calls about:
    *   [1] self-closing the active child "View", and
    *   [2] redirecting to / loading of a new child "View" on top of the active one
    * */
    protected val dispatcher: Dispatcher by inject()

    /*
    * By default, the member variable "loadingDialog" already observes and reacts
    * upon the partner viewModel (if there's any returned by getBaseViewModel()).
    * Although, it can still be tweaked and be shown on your other scenarios.
    * */
    protected val loadingDialog by lazy { LoadingDialog() }

    /*
    * The "messageDialog" member variable is ready for your usage.
    * Use methods "messageDialog.updateArguments()" and "messageDialog.safelyOpen()" to manipulate it.
    * For more information on the said dialog, visit MessageDialog class.
    * */
    protected val messageDialog by lazy { MessageDialog() }

    /*
    * The XML layout of the "View" may or may not have a Toolbar of its own.
    *   If it is the former, the getToolbarSetting() function must return a ToolbarSetting.NonRecycled value.
    *   Otherwise, the said function must return a ToolbarSetting.Recycled value.
    * */
    abstract fun getToolbarSetting(): ToolbarSetting

    /*
    * getBaseViewModel() => Not all Fragments in this app are required to have
    * a ViewModel partnered to them. This is in cases where a Fragment is for
    * displaying static elements. (Example: AboutView)
    *
    * The reason for the returned value being nullable is for those fragments.
    * */
    protected abstract fun getBaseViewModel(): BaseViewModel?
    protected abstract fun getLayoutResourceId(): Int
    protected abstract fun prepareElements()
    protected abstract fun prepareObservables()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(getLayoutResourceId())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        logDebug(this.javaClass.simpleName, "onActivityCreated()")
        prepareLoadingDialog()
        prepareObservables()

        prepareElements()
    }

    override fun onDetach() {
        loadingDialog.safelyClose()
        messageDialog.safelyClose()

        super.onDetach()
    }

    override fun onDestroy() {
        getBaseViewModel()?.release()
        super.onDestroy()
    }

    private fun prepareLoadingDialog() {
        getBaseViewModel()?.let {
                baseViewModel ->
                    baseViewModel.isLoading.observe(this, Observer {
                if(it == true) {
                    logDebug(message = "Loading value is true")
                    loadingDialog.safelyOpen(fragmentManager, LoadingDialog.TAG)
                }
                else loadingDialog.safelyClose()
            })
        }
    }

}