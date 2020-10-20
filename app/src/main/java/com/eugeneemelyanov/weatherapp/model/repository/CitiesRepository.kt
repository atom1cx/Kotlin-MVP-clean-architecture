package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.presentation.models.City
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*

interface CitiesRepository {
    fun getCities() : Flowable<List<City>>
    fun saveCity(cityName: String): Observable<City>
}