package com.regaslzr.kprojstarter.data.sampleapi.joke.model

import com.google.gson.annotations.SerializedName
import com.regaslzr.kprojstarter.R

/*
* Taken from: https://official-joke-api.appspot.com/random_joke
* Sample JSON:
* {"id":273,"type":"general","setup":"What musical instrument is found in the bathroom?","punchline":"A tuba toothpaste."}
* */


class Joke(@SerializedName("id") val id: Int,
           @SerializedName("type") val type: String,
           @SerializedName("setup") val setup: String,
           @SerializedName("punchline") val punchline: String) {

    fun getRandomEmoji() : Int {
        return listEmoji.random()
    }

    companion object {
        private val listEmoji : List<Int> = listOf(
            R.drawable.drawable_emoji_blank,
            R.drawable.drawable_emoji_dead,
            R.drawable.drawable_emoji_evil,
            R.drawable.drawable_emoji_happy,
            R.drawable.drawable_emoji_smile,
            R.drawable.drawable_emoji_tongue)
    }

}