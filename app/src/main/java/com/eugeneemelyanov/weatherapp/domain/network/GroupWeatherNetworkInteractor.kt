package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import com.eugeneemelyanov.weatherapp.model.remote.models.GroupWeatherResponse
import io.reactivex.Single

interface GroupWeatherNetworkInteractor {
    fun getGroupWeather(ids: String) : Single<GroupWeatherResponse>
}