package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.remote.OpenWeatherMapApi
import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CityWeatherNetworkInteractorImpl @Inject constructor(
    val apiService: OpenWeatherMapApi,
    val databaseInteractor: CityDatabaseInteractor
) : CityWeatherNerworkInteractor {
    override fun getCityWeather(city: String): Single<CityDataEntity> {
        return apiService.getWeather(city)
            .map {
                    response -> CityDataEntity.fromResponse(response)
            }
            .doOnSuccess {
                    data ->  databaseInteractor.saveCity(data)
            }
            .doOnError {
                error ->
                Timber.d(error)
            }
    }
}