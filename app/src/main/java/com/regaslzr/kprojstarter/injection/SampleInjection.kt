package com.regaslzr.kprojstarter.injection

import android.app.Application
import com.regaslzr.kprojstarter.data.sampleapi.genderize.source.GenderizeApiClient
import com.regaslzr.kprojstarter.data.sampleapi.genderize.usecases.UseCaseGenderizeName
import com.regaslzr.kprojstarter.data.sampleapi.joke.source.JokeApiClient
import com.regaslzr.kprojstarter.data.sampleapi.joke.usecases.UseCaseGetRandomJoke
import com.regaslzr.kprojstarter.base.view.screenmanager.Dispatcher
import com.regaslzr.kprojstarter.base.view.screenmanager.ScreenManager
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module

import org.koin.dsl.module.module
import org.koin.log.EmptyLogger

fun Application.prepareInjections() {
    startKoin(this,
        listOf(
            getDispatcher(),
            getGenderizeUseCases(),
            getJokeUseCases()
            /*add all the other module of useCases here...*/
        ),
        logger = EmptyLogger())
}

private fun getDispatcher() : Module {
    return module {
        val screenManager = ScreenManager()
        single { screenManager }
        single { screenManager as Dispatcher }
    }
}

private fun getJokeUseCases() : Module {
    val source = JokeApiClient().getApiSource()
    return module {
        factory { UseCaseGetRandomJoke(source) }
    }
}

private fun getGenderizeUseCases() : Module {
    val source = GenderizeApiClient().getApiSource()
    return module {
        factory { UseCaseGenderizeName(source) }
    }
}