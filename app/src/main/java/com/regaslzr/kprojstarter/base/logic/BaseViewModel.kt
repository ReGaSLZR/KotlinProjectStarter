package com.regaslzr.kprojstarter.base.logic

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.regaslzr.kprojstarter.common.extensions.parseFailure
import com.regaslzr.kprojstarter.data.model.Failure
import com.regaslzr.kprojstarter.common.extensions.logError
import com.regaslzr.kprojstarter.common.extensions.logWarning
import io.reactivex.disposables.CompositeDisposable

/*
* This class is to be the superclass of all ViewModels in the application.
*
* ViewModels contain:
*  [1] live data that their partner Views (Fragments) observe and react upon,
*  [2] compositeDisposables for storing and disposing of the streams from UseCase calls to prevent leaks.
*
* The children ViewModels may or may not have "UseCases" injected into them.
*   (See BaseUseCase class for information on "UseCases")
*
* Usage Examples:
*       - GenderizeNameViewModel
*       - RandomJokeViewModel
* */
abstract class BaseViewModel : ViewModel() {

    val failure = MutableLiveData<Failure>()
    val isLoading = MutableLiveData<Boolean>()

    var disposables = CompositeDisposable()

    fun release() {
        logWarning(this.javaClass.simpleName, "release() called")

        disposables.dispose()
        disposables.clear()

        //we need to refresh this so that succeeding calls
        //will push through (if the viewModel gets reused)
        disposables = CompositeDisposable()
    }

    fun handleErrorResponse(error: Throwable) {
        logError(this.javaClass.simpleName, "error is: ${error.message}")

        isLoading.value = false
        failure.value = error.parseFailure()
    }

}