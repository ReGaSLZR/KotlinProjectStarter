package com.regaslzr.kprojstarter.samplepages.randomjoke

import android.arch.lifecycle.MutableLiveData
import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import com.regaslzr.kprojstarter.data.sampleapi.joke.model.Joke
import com.regaslzr.kprojstarter.data.sampleapi.joke.usecases.UseCaseGetRandomJoke
import com.regaslzr.kprojstarter.common.extensions.logDebug

class RandomJokeViewModel(private val useCaseGetRandomJoke: UseCaseGetRandomJoke) : BaseViewModel() {

    val randomJoke = MutableLiveData<Joke>()

    fun loadJoke() {
        isLoading.value = true
        useCaseGetRandomJoke.execute(::handleRandomJokeResponse, this)
    }

    private fun handleRandomJokeResponse(newJoke: Joke) {
        logDebug(TAG, "handleRandomJokeResponse() new joke received.")
        randomJoke.value = newJoke
        isLoading.value = false
    }

    companion object {
        private val TAG = RandomJokeViewModel::class.java.simpleName
    }

}