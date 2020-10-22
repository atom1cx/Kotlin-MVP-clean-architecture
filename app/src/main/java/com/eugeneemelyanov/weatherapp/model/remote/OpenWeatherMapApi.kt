package com.eugeneemelyanov.weatherapp.model.remote

import com.eugeneemelyanov.weatherapp.model.remote.models.CityForecastResponse
import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import com.eugeneemelyanov.weatherapp.model.remote.models.GroupWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("weather")
    fun getWeather(@Query("q") city: String): Single<CityWeatherResponse>

    @GET("group")
    fun getGroupWeather(@Query("id") ids: String): Single<GroupWeatherResponse>

    @GET("forecast")
    fun getForecast(@Query("q") city: String, @Query("cnt") count: Int = 25): Single<CityForecastResponse>
}