package com.regaslzr.kprojstarter.common.toolbar

import com.regaslzr.kprojstarter.R


/*
 * "NonRecycled" means that the setting won't reuse any shared/existing toolbar
 * (e.g. activity's toolbar) and that the view will provide its own
 *
 * "Recycled" means that the view with this setting will use the shared/existing toolbar, only
 * replacing the previous iconResource and titleResource to suit the view
 */
sealed class ToolbarSetting {
    object NonRecycled : ToolbarSetting()
    data class Recycled(val iconResource: Int = R.drawable.drawable_ic_back_black,
                        val titleResource: Int) : ToolbarSetting()
}