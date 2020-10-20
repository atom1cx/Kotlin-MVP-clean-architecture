package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.network.CityWeatherNerworkInteractor
import com.eugeneemelyanov.weatherapp.presentation.models.City
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor (
    val databaseInteractor: CityDatabaseInteractor,
    val networkInteractor: CityWeatherNerworkInteractor
) :  CitiesRepository{

    var dataProviderDisposable: Disposable? = null

    override fun getCities(): Flowable<List<City>> {
        return databaseInteractor.getCities().map { list -> list.map { item -> City.fromEntity(item) } }
    }

    override fun saveCity(cityName: String): Observable<City> {
        return networkInteractor.getCityWeather(cityName).toObservable().map {
            x -> City.fromEntity(x)
        }
    }

    private fun isNetworkInProgress() : Boolean {
        return dataProviderDisposable != null && dataProviderDisposable?.isDisposed ?:true
    }
}