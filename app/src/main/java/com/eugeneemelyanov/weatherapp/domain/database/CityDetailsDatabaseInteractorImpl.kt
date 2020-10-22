package com.eugeneemelyanov.weatherapp.domain.database

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import com.eugeneemelyanov.weatherapp.model.db.WeatherDatabase
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CityDetailsDatabaseInteractorImpl @Inject constructor(
    val database: WeatherDatabase
)  : CityDetailsDatabaseInteractor {

    override fun getCityWeather(id: Long): Flowable<List<CityWeatherEntity>> {
        return database.weatherDao().getWeather(id)
            .observeOn(Schedulers.io())
    }

    override fun saveData(data: List<CityWeatherEntity>) {
        database.weatherDao().saveData(data)
    }
}