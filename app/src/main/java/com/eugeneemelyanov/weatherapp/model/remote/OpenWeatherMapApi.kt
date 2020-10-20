package com.eugeneemelyanov.weatherapp.model.remote

import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import com.eugeneemelyanov.weatherapp.model.remote.models.GroupWeatherResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("weather")
    fun getWeather(@Query("q") city: String): Single<CityWeatherResponse>

    @GET("group")
    fun getGroupWeather(@Query("id") ids: String): Single<GroupWeatherResponse>

}