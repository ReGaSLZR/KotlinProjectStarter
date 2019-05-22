package com.regaslzr.kprojstarter.common.extensions

import com.regaslzr.kprojstarter.base.logic.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.fromRemote(): Observable<T> =
    this.subscribeOn(Schedulers.io()).observeOnMain()

fun <T> Observable<T>.fromDatabase(): Observable<T> =
    this.subscribeOn(Schedulers.computation()).observeOnMain()

fun <T> Observable<T>.fromMain(): Observable<T>  =
    this.subscribeOn(AndroidSchedulers.mainThread()).observeOnMain()

fun <T> Observable<T>.remoteConfig(successFunc: (response: T) -> Unit,
                                    viewModel: BaseViewModel
) =
    this.fromRemote()
        .subscribe(successFunc, viewModel::handleErrorResponse)
        .addTo(viewModel.disposables)

private fun <T> Observable<T>.observeOnMain(): Observable<T> =
    this.observeOn(AndroidSchedulers.mainThread())