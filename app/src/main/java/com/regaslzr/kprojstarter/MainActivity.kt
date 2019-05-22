package com.regaslzr.kprojstarter

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.regaslzr.kprojstarter.base.view.BaseActivity
import com.regaslzr.kprojstarter.base.view.BaseView
import com.regaslzr.kprojstarter.common.toolbar.ProjectToolbar
import com.regaslzr.kprojstarter.common.toolbar.ToolbarSetting
import com.regaslzr.kprojstarter.samplepages.about.AboutView
import com.regaslzr.kprojstarter.samplepages.genderizename.GenderizeNameView
import com.regaslzr.kprojstarter.samplepages.randomjoke.RandomJokeView
import com.regaslzr.kprojstarter.common.extensions.logDebug
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_view_main_navigation.*

class MainActivity : BaseActivity(), ProjectToolbar.IconClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity_main_toolbar.listener = this
        prepareObservables()
    }

    override fun getChangeableContentId(): Int = R.id.activity_main_screen_content

    override fun getHomeScreen(): BaseView = AboutView()

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onChangeScreen() {
        applyToolbarSettings()
        applyDrawerLockSettings()
    }

    override fun onToolbarIconClick() {
        if(isDrawerAvailableForCurrentScreen()) {
            logDebug(this.javaClass.simpleName, "Opening the drawer...")
            openDrawer()
        }
        else {
            screenManager.goBack()
        }
    }

    private fun applyToolbarSettings() {
        val toolbarSetting = screenManager.currentScreen.value?.getToolbarSetting()
            ?: ToolbarSetting.NonRecycled

        if(toolbarSetting is ToolbarSetting.Recycled) {
            activity_main_toolbar.visibility = View.VISIBLE
            activity_main_toolbar.setIconResource(toolbarSetting.iconResource)
            activity_main_toolbar.setTitleResource(toolbarSetting.titleResource)
        }
        else {
            activity_main_toolbar.visibility = View.GONE
        }
    }

    private fun applyDrawerLockSettings() {
        setDrawerLock(!isDrawerAvailableForCurrentScreen())
    }

    private fun closeDrawer() {
        activity_main_drawerlayout.closeDrawer(GravityCompat.START)
    }

    private fun openDrawer() {
        activity_main_drawerlayout.openDrawer(GravityCompat.START)
    }

    private fun prepareObservables() {
        main_drawer_menu_text_genderize_name.setOnClickListener {
            screenManager.dispatch(GenderizeNameView())
            closeDrawer()
        }

        main_drawer_menu_text_random_joke.setOnClickListener {
            screenManager.dispatch(RandomJokeView())
            closeDrawer()
        }

        main_drawer_header_title.setOnClickListener {
            screenManager.dispatch(AboutView())
            closeDrawer()
        }
    }

    private fun setDrawerLock(isLocked: Boolean) {
        activity_main_drawerlayout.setDrawerLockMode(
            if(isLocked) DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            else DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun isDrawerAvailableForCurrentScreen(): Boolean = screenManager.isCurrentScreenHome()

}
