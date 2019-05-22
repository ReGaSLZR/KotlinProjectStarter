package com.regaslzr.kprojstarter.data.sampleapi.joke.usecases

import com.regaslzr.kprojstarter.base.logic.BaseUseCase
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.common.extensions.remoteConfig
import com.regaslzr.kprojstarter.data.sampleapi.joke.model.Joke
import com.regaslzr.kprojstarter.data.sampleapi.joke.source.JokeApiSource
import com.regaslzr.kprojstarter.common.extensions.logDebug

class UseCaseGetRandomJoke(private val source: JokeApiSource) : BaseUseCase<Joke>() {

    override fun execute(successFunc: (result: Joke) -> Unit, baseViewModel: BaseViewModel) {
        logDebug(TAG, "Getting a joke via execute() call. :)")
        source.getAJoke()
            .remoteConfig(successFunc, baseViewModel)
    }

    companion object {
        private val TAG = UseCaseGetRandomJoke::class.java.simpleName
    }

}