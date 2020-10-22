package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import io.reactivex.Single

interface GroupWeatherNetworkInteractor {
    fun getGroupWeather(ids: List<Long>) : Single<List<CityDataEntity>>
}