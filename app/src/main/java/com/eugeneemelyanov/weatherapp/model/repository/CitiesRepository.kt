package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.presentation.models.City
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface CitiesRepository {
    fun getCities() : Flowable<List<City>>
    fun saveCity(cityName: String): Observable<City>
    fun refreshCities(): Completable
}