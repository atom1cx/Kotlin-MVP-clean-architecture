package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import io.reactivex.Single

interface CityWeatherNerworkInteractor {
    fun getCityWeather(city: String) : Single<CityDataEntity>
}