package com.regaslzr.kprojstarter.common.dialog

import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.base.view.BaseDialog
import com.regaslzr.kprojstarter.common.extensions.logDebug


/**
 * This is a dialog containing:
 * [1] a Progressbar, and
 * [2] lines of text saying, "Loading<br/>Please wait..."
 *
 * Cannot be cancelled via any user interaction, unless its host explicitly calls dismiss() or safelyClose()
 * The text "Loading<br/>Please wait..." cannot be changed runtime. (This is by design)
 */
class LoadingDialog : BaseDialog() {

    override fun applyArguments() {
        logDebug(TAG, "applyArguments()")
        //NOTE: there are no arguments available for applying in this class at the moment
    }

    override fun getLayoutResourceId(): Int = R.layout.dialog_loading

    companion object {
        val TAG: String = LoadingDialog::class.java.simpleName
    }

}