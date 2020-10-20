package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.network.CityWeatherNerworkInteractor
import com.eugeneemelyanov.weatherapp.domain.network.GroupWeatherNetworkInteractor
import com.eugeneemelyanov.weatherapp.presentation.models.City
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor (
    private val databaseInteractor: CityDatabaseInteractor,
    private val networkInteractor: CityWeatherNerworkInteractor,
    private val groupWeatherNetworkInteractor: GroupWeatherNetworkInteractor
) :  CitiesRepository{

    override fun getCities(): Flowable<List<City>> {
        return databaseInteractor.getCities().map { list -> list.map { item -> City.fromEntity(item) } }
    }

    override fun saveCity(cityName: String): Observable<City> {
        return networkInteractor.getCityWeather(cityName).toObservable().map { x ->
            City.fromEntity(x)
        }
    }

    override fun refreshCities(): Completable {
        val databaseObs = databaseInteractor.getCities().firstOrError()

        return databaseObs.map {
            cities -> cities.map { it.id }
        }.flatMap { ids ->  groupWeatherNetworkInteractor.getGroupWeather(ids)}.toCompletable()
    }
}