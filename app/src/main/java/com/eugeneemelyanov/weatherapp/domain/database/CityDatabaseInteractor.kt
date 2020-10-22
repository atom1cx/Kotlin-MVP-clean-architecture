package com.eugeneemelyanov.weatherapp.domain.database

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import io.reactivex.Flowable

interface CityDatabaseInteractor {
    fun saveCity(data: CityDataEntity)
    fun saveCities(data: List<CityDataEntity>)
    fun getCities(): Flowable<List<CityDataEntity>>
    fun getCity(id: Long): Flowable<CityDataEntity>
}