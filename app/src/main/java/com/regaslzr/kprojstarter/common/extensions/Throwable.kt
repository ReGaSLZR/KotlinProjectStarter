package com.regaslzr.kprojstarter.common.extensions

import com.regaslzr.kprojstarter.data.model.Failure
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by rgsalazar on 10/18/18.
 */
fun Throwable.parseFailure(): Failure {
    this.message?.let {
        logWarning(this.javaClass.simpleName, it)
    }

    return when(this) {
        is UnknownHostException,
        is SocketTimeoutException,
        is ConnectException -> {
            Failure.Network
        }
        is TimeoutException -> {
            Failure.Timeout
        }
        is HttpException -> {
            parseHttpException(this)
        }
        else -> {
            Failure.Generic
        }
    }

}

private fun parseHttpException(exception: HttpException): Failure {
    return try {
        val responseBody: ResponseBody? = exception.response().errorBody()
        val jsonObject = JSONObject(responseBody.toString())

        val message: String =
            if(jsonObject.has("error")) jsonObject.getString("error")
            else if(jsonObject.has("message")) jsonObject.getString("message")
            else if(jsonObject.has("errorMessage")) jsonObject.getString("errorMessage")
            else "Received API error."

        Failure.Api(exception.code(), message)
    }
    catch(je: JSONException) {
        logWarning(message = "Cannot parse Error body")
        Failure.Server
    }
}