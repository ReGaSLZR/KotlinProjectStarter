package com.regaslzr.kprojstarter.common.toolbar

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.common.extensions.logDebug
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class ProjectToolbar : Toolbar {

    interface IconClickListener {
        fun onToolbarIconClick()
    }

    var listener: IconClickListener? = null

    constructor(context: Context): this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        View.inflate(context, R.layout.custom_toolbar, this)
        setDisplayValues(attrs)
        setListener()
    }

    fun setIconResource(icon: Int) {
        toolbar_imageView.setImageResource(icon)
    }

    fun setTitleResource(title: Int) {
        toolbar_textView.text = context.getString(title)
    }

    private fun setDisplayValues(attrs: AttributeSet?) {
        logDebug(TAG, "setDisplayValues()")

        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.ProjectToolbar, 0, 0)
            try {

                //setting the icon for the ImageView
                val icon = ta.getDrawable(R.styleable.ProjectToolbar_toolbarIcon)
                icon?.let {
                    toolbar_imageView.setImageDrawable(icon)
                }

                //setting the title for the TextView
                val title = ta.getString(R.styleable.ProjectToolbar_toolbarTitle)
                title?.let {
                    toolbar_textView.text = title
                }

            } finally {
                ta.recycle()
            }
        }
    }

    private fun setListener() {
        toolbar_imageView.setOnClickListener { listener?.onToolbarIconClick() }
    }

    companion object {
        private val TAG: String = ProjectToolbar::class.java.simpleName
    }

}