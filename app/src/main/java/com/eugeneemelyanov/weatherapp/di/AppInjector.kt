package com.eugeneemelyanov.weatherapp.di

import com.eugeneemelyanov.weatherapp.WeatherApplication

object AppInjector {
    fun inject(app: WeatherApplication) {
        DaggerAppComponent.builder()
            .withContext(app)
            .build()
            .inject(app)
    }
}