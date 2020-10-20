package com.eugeneemelyanov.weatherapp.domain.database

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.WeatherDatabase
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CityDatabaseInteractorImpl @Inject constructor(
val database: WeatherDatabase
) : CityDatabaseInteractor {
    override fun saveCity(data: CityDataEntity) {
        database.cityDao().saveData(data)
    }

    override fun saveCities(data: List<CityDataEntity>) {
        data.forEach {
            saveCity(it)
        }
    }

    override fun getCities(): Flowable<List<CityDataEntity>> {
        return database.cityDao().getCities()
            .observeOn(Schedulers.io())
    }
}