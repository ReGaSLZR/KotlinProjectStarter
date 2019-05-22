package com.regaslzr.kprojstarter.samplepages.randomjoke

import android.arch.lifecycle.Observer
import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.base.view.BaseView
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.extensions.showToast
import com.regaslzr.kprojstarter.common.toolbar.ToolbarSetting
import kotlinx.android.synthetic.main.fragment_joke.*
import kotlinx.android.synthetic.main.include_joke.*
import org.koin.android.ext.android.get

class RandomJokeView : BaseView() {

    private val viewModel by lazy { RandomJokeViewModel(get()) }

    override fun getToolbarSetting(): ToolbarSetting =
        ToolbarSetting.Recycled(R.drawable.drawable_emoji_happy, R.string.toolbar_title_random_joke)

    override fun getBaseViewModel(): BaseViewModel? = viewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_joke

    override fun prepareElements() {
        imageView_refresh.callOnClick()
    }

    override fun prepareObservables() {
        imageView_refresh.setOnClickListener {
            viewModel.loadJoke()
        }

        viewModel.failure.observe(this, Observer {
            context?.showToast(it?.message ?: "Failure observed.")
        })

        viewModel.randomJoke.observe(this, Observer {
            it?.let {
                value_jokeId.text = it.id.toString()
                value_jokeType.text = it.type.capitalize()
                value_jokeSetup.text = it.setup
                value_jokePunchline.text = it.punchline

                imageView_emoji.setImageResource(it.getRandomEmoji())
            }
        })
    }

    companion object {
        val TAG = RandomJokeView::class.java.simpleName
    }

}