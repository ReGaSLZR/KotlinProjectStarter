package com.regaslzr.kprojstarter.samplepages.genderizename

import android.arch.lifecycle.MutableLiveData
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.data.sampleapi.genderize.model.GenderizedName
import com.regaslzr.kprojstarter.data.sampleapi.genderize.usecases.UseCaseGenderizeName

class GenderizeNameViewModel(private val useCaseGenderizeName: UseCaseGenderizeName) : BaseViewModel() {

    val genderizedName = MutableLiveData<GenderizedName>()

    fun genderizeName(name: String) {
        isLoading.value = true

        useCaseGenderizeName.param = UseCaseGenderizeName.Param(name)
        useCaseGenderizeName.execute(::handleGenderizedNameResponse, this)
    }

    private fun handleGenderizedNameResponse(newName: GenderizedName) {
        genderizedName.value = newName
        isLoading.value = false
    }

}