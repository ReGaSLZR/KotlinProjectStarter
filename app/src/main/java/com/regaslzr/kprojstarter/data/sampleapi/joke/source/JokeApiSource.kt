package com.regaslzr.kprojstarter.data.sampleapi.joke.source

import com.regaslzr.kprojstarter.data.sampleapi.joke.model.Joke
import io.reactivex.Observable
import retrofit2.http.GET

interface JokeApiSource {

    @GET("random_joke")
    fun getAJoke() : Observable<Joke>

    @GET("random_ten")
    fun getTenJokes() : Observable<List<Joke>>

}