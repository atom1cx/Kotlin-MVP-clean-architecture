package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.domain.ForecastMappableEntity
import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.database.CityDetailsDatabaseInteractorImpl
import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import com.eugeneemelyanov.weatherapp.model.remote.OpenWeatherMapApi
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CityForecastNetworkInteractorImpl @Inject constructor(
    private val apiService: OpenWeatherMapApi,
    private val databaseInteractor: CityDatabaseInteractor,
    private val weatherdbInteractor: CityDetailsDatabaseInteractorImpl
) : CityForecastNetworkInteractor {
    override fun getCityForecast(name: String): Completable {
        return apiService.getForecast(name)
            .map {
                    response -> ForecastMappableEntity.fromResponse(response)
            }
            .doOnSuccess {
                    data ->  databaseInteractor.saveCity(data.cityInfo)
            }
            .doOnError {
                    error ->
                Timber.d(error)
            }
            .doOnSuccess {
                data -> weatherdbInteractor.saveData(data.weather)
            }.toCompletable()
    }
}