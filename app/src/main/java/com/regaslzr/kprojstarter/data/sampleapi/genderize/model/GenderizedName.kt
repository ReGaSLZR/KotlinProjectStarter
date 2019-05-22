package com.regaslzr.kprojstarter.data.sampleapi.genderize.model

import com.google.gson.annotations.SerializedName
import com.regaslzr.kprojstarter.R

/*
* From: https://genderize.io/
* The count represents the number of data entries examined in order to calculate the response.
* */

class GenderizedName(@SerializedName("name") val name : String,
                     @SerializedName("gender") val gender : String?,
                     @SerializedName("probability") val probability : Double,
                     @SerializedName("count") val count : Int) {

    fun getProbabilityPercent() : String =
        (probability * 100).toString().plus("%")

    fun getGenderAvatarResource() : Int =
        if(gender.equals(GENDER_MALE)) R.drawable.drawable_av_male
        else if(gender.equals(GENDER_FEMALE)) R.drawable.drawable_av_female
        else R.drawable.drawable_av_unknown

    companion object {
        private val GENDER_MALE = "male"
        private val GENDER_FEMALE = "female"
    }

}

