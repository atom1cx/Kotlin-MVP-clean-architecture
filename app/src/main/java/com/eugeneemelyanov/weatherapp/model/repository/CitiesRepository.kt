package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.presentation.models.City
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetailed
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface CitiesRepository {
    fun getCities() : Flowable<List<City>>
    fun saveCity(cityName: String): Observable<City>
    fun refreshCities(): Completable
    fun getCityDetailed(id: Long): Flowable<CityDetailed>
    fun getWeather(id: Long): Flowable<List<Weather>>
    fun getCityForecast(name: String): Completable
}