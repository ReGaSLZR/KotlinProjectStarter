package com.regaslzr.kprojstarter.samplepages.about

import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.base.view.BaseView
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.toolbar.ToolbarSetting

class AboutView : BaseView() {
    override fun getToolbarSetting(): ToolbarSetting =
        ToolbarSetting.Recycled(R.mipmap.ic_launcher, R.string.toolbar_title_About)

    override fun getBaseViewModel(): BaseViewModel? = null

    override fun getLayoutResourceId(): Int = R.layout.fragment_about

    override fun prepareElements() {
        //no interactable elements
    }

    override fun prepareObservables() {
        //none
    }

    companion object {
        val TAG = AboutView::class.java.simpleName
    }

}