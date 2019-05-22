package com.regaslzr.kprojstarter.base.logic

/*
* This class is to be the superclass of all UseCases in the project.
*
* A "UseCase" represents 1 endpoint from an "ApiSource" and
*   is intended to be injected into BaseViewModel children.
*
* An "ApiSource" is an interface where you declare a collection of endpoints
*   that belong together and have the same baseUrl (as declared in ApiClient).
*
* "T" represents the type of the object to be received by the "successFunc".
*       Hence, it is the "T" in "Observable<T>" returned by the ApiSource endpoint.
*
* For UseCases that have additional parameters needed by the ApiSource endpoint call,
*       declare an inner class called "Param" (with the actual parameters as its variables),
*       and a member variable of its type (which will be set/updated by the UseCase holder).
*
* Examples:
*       - UseCaseGetRandomJoke (no parameters),
*       - UseCaseGenderizeName (with parameter)
* */
abstract class BaseUseCase<T> {

    abstract fun execute(successFunc: (result: T) -> Unit,
                baseViewModel: BaseViewModel)

}