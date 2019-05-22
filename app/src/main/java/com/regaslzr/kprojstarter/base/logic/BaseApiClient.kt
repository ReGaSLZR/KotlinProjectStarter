package com.regaslzr.kprojstarter.base.logic

import com.regaslzr.kprojstarter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* This class is to be the superclass of any Retrofit API client.
*
* "T" represents the interface where all your endpoints are declared.
* "classToken" is the explicit value of T::class.java to be passed on by the child class.
*               This value is explicitly needed because the value "T::class.java" cannot be used.
*               (Cannot use "T" as a reified type parameter.)
* "baseUrl" is the String URL (make sure it ends with "/") of the API Source to be created.
*
* Examples:
*       - GenderizeApiClient,
*       - JokeApiClient
* */
abstract class BaseApiClient<T: Any>(val classToken: Class<T>, val baseUrl: String) {

    fun getApiSource(): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(classToken) //cannot use "T::class.java"

    protected fun createClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(createLoggingInterceptor())
            .build()

    protected fun createLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60
    }

}