package com.regaslzr.kprojstarter.data.sampleapi.joke.source

import com.regaslzr.kprojstarter.BuildConfig
import com.regaslzr.kprojstarter.base.logic.BaseApiClient

class JokeApiClient : BaseApiClient<JokeApiSource>(
    JokeApiSource::class.java, BuildConfig.JOKE_BASE_URL)