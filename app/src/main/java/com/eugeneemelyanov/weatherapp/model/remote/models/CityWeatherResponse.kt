package com.eugeneemelyanov.weatherapp.model.remote.models

import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZonedDateTime

data class CityWeatherResponse(
    @SerializedName("id")
val id: Long,
    @SerializedName("dt")
val dateTime: ZonedDateTime,
    @SerializedName("name")
val cityName : String,
    @SerializedName("weather")
val weather: List<Weather>,
    @SerializedName("sys")
val sys: Sys,
    @SerializedName("wind")
val wind: Wind,
    @SerializedName("main")
val main: Main,
    @SerializedName("visibility")
val visibility: Int
)

data class Main(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

data class Wind(
    @SerializedName("speed")
    val speed: Int
)

data class Sys(
    @SerializedName("sunrise")
    val sunrise: ZonedDateTime,
    @SerializedName("sunset")
    val sunset: ZonedDateTime
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)