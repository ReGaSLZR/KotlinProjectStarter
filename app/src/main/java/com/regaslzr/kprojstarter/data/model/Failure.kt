package com.regaslzr.kprojstarter.data.model

sealed class Failure(val message: String) {
    object Network : Failure("No internet connection.")
    object Timeout : Failure("Connection timed out.")
    object Server : Failure("Server error.")
    data class Api(val errorCode: Int, val errorMessage: String) : Failure(errorMessage)

    object Generic : Failure("Something went wrong.")
}