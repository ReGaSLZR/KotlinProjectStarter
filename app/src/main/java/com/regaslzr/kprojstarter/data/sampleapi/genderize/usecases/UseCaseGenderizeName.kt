package com.regaslzr.kprojstarter.data.sampleapi.genderize.usecases

import com.regaslzr.kprojstarter.base.logic.BaseUseCase
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.extensions.remoteConfig
import com.regaslzr.kprojstarter.data.sampleapi.genderize.model.GenderizedName
import com.regaslzr.kprojstarter.data.sampleapi.genderize.source.GenderizeApiSource
import com.regaslzr.kprojstarter.common.extensions.logDebug
import com.regaslzr.kprojstarter.common.extensions.logWarning

class UseCaseGenderizeName(private val source: GenderizeApiSource): BaseUseCase<GenderizedName>() {

    class Param(val name: String)

    var param: Param? = null

    override fun execute(successFunc: (result: GenderizedName) -> Unit, baseViewModel: BaseViewModel) {
        if(param == null) {
            logWarning(TAG, "Param is not set. Cannot proceed with execute() call.")
            return
        }

        logDebug(TAG, "Getting Genderized name with param = '${param!!.name}'")
        source.getGenderizedName(param!!.name)
            .remoteConfig(successFunc, baseViewModel)
    }

    companion object {
        private val TAG = UseCaseGenderizeName::class.java.simpleName
    }

}