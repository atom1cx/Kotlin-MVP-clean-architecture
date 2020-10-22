package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CityForecastNetworkInteractor {
    fun getCityForecast(name: String) : Completable
}