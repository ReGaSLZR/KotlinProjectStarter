package com.regaslzr.kprojstarter.data.sampleapi.genderize.source

import com.regaslzr.kprojstarter.data.sampleapi.genderize.model.GenderizedName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderizeApiSource {
    @GET(".")
    fun getGenderizedName(@Query("name") name : String)
            : Observable<GenderizedName>
}