package com.eugeneemelyanov.weatherapp.model.remote.models

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters.CityDetailsAdapter
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

data class CityForecastResponse(
    @SerializedName("list")
        val forecastData: List<ForecastData>,
        @SerializedName("city")
        val city: City,
        @SerializedName("sunrise")
        val sunrise: Long,
        @SerializedName("sunset")
        val sunset: Long
) {
    companion object {
        fun mapToWeatherList(resp: CityForecastResponse) : List<CityWeatherEntity> {
            return resp.forecastData.map {
                forecastData ->  CityWeatherEntity().also {
                it.id = resp.city.id
                it.position = resp.forecastData.indexOf(forecastData)
                it.currentTemperature = forecastData.main.temp.roundToInt()
                it.weatherIcon = forecastData.weather[0].icon
                it.dt = forecastData.dt
            }
            }
        }

        fun mapToCityEntity(resp: CityForecastResponse) : CityDataEntity {
            var entity = CityDataEntity()
            entity.id = resp.city.id
            entity.cityName = resp.city.name
            entity.currentTemperature = resp.forecastData[0].main.temp.roundToInt()
            entity.feelsLike = resp.forecastData[0].main.feelsLike.roundToInt()
            entity.weatherIcon = resp.forecastData[0].weather[0].icon
            entity.pressure = resp.forecastData[0].main.pressure
            entity.humidity = resp.forecastData[0].main.humidity
            entity.visibility = resp.forecastData[0].visibility
            entity.windSpeed = resp.forecastData[0].wind.speed.roundToInt()
            entity.sunrise = resp.sunrise
            entity.sunrise = resp.sunset

            return entity
        }
    }
}

data class City(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)

data class ForecastData(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: ForecastMain,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: WindFloat,
    @SerializedName("visibility")
    val visibility: Int
)

data class ForecastMain(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

