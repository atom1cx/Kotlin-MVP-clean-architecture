package com.eugeneemelyanov.weatherapp.domain.database

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import io.reactivex.Flowable

interface CityDetailsDatabaseInteractor {
    fun getCityWeather(id: Long): Flowable<List<CityWeatherEntity>>
    fun saveData(data: List<CityWeatherEntity>)
}