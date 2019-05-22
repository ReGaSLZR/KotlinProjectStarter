package com.regaslzr.kprojstarter.samplepages.genderizename

import android.arch.lifecycle.Observer
import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.base.view.BaseView
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.extensions.showToast
import com.regaslzr.kprojstarter.common.toolbar.ToolbarSetting
import kotlinx.android.synthetic.main.fragment_genderize_name.*
import org.koin.android.ext.android.get

class GenderizeNameView : BaseView() {

    private val viewModel by lazy { GenderizeNameViewModel(get()) }

    override fun getToolbarSetting(): ToolbarSetting =
        ToolbarSetting.Recycled(R.drawable.drawable_av_unknown, R.string.toolbar_title_genderize_name)

    override fun getBaseViewModel(): BaseViewModel? = viewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_genderize_name

    override fun prepareElements() {
        messageDialog.updateArguments(message = "Please input name.")
    }

    override fun prepareObservables() {
        button_findGender.setOnClickListener{
            if(editText_name.text.toString().isBlank()) {
                messageDialog.safelyOpen(fragmentManager, TAG)
            }

            else {
                viewModel.genderizeName(editText_name.text.toString())
            }
        }

        viewModel.genderizedName.observe(this, Observer {
            it?.let {
                editText_name.setText("")

                value_nameEntry.text = it.name.capitalize()
                value_nameGender.text = it.gender?.capitalize() ?: "???"
                value_nameProbability.text = it.getProbabilityPercent()

                imageView_gender.setImageResource(it.getGenderAvatarResource())
            }
        })

        viewModel.failure.observe(this, Observer {
            context?.showToast(it?.message ?: "Failure observed.")
        })
    }

    companion object {
        private val TAG = GenderizeNameView::class.java.simpleName
    }

}