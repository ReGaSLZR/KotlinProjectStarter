package com.regaslzr.kprojstarter.data.sampleapi.genderize.source

import com.regaslzr.kprojstarter.BuildConfig
import com.regaslzr.kprojstarter.base.logic.BaseApiClient

class GenderizeApiClient : BaseApiClient<GenderizeApiSource>(
    GenderizeApiSource::class.java,
    BuildConfig.GENDERIZE_BASE_URL)