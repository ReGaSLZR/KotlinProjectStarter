package com.regaslzr.kprojstarter.base.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.regaslzr.kprojstarter.common.extensions.logDebug

abstract class BaseDialog : DialogFragment() {

    protected abstract fun getLayoutResourceId() : Int
    protected abstract fun applyArguments()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        logDebug(this.javaClass.simpleName, "onCreateView()")
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false

        return inflater.inflate(getLayoutResourceId(), container)
    }

    override fun onStart() {
        super.onStart()
        setLayoutFullWidth()
    }

    private fun setLayoutFullWidth() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
    }

    fun safelyOpen(fragmentManager: FragmentManager?, tag: String) {
        if(!isVisible || !isAdded) {
            show(fragmentManager, tag)
        }
    }

    fun safelyClose() {
        if(isVisible || isAdded) dismiss()
    }

}