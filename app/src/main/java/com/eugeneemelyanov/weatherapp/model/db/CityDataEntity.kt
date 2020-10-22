package com.eugeneemelyanov.weatherapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import java.util.*
import kotlin.math.roundToInt

@Entity(tableName = "city_data")
class CityDataEntity {

    @PrimaryKey
    @ColumnInfo(name = "cityId")
    var id: Long = 0
    @ColumnInfo(name = "cityName")
    var cityName: String = ""
    @ColumnInfo(name = "currentTemperature")
    var currentTemperature: Int = 0
    @ColumnInfo(name = "feelsLike")
    var feelsLike: Int = 0
    @ColumnInfo(name = "weatherIcon")
    var weatherIcon: String = ""
    @ColumnInfo(name = "pressure")
    var pressure: Int = 0
    @ColumnInfo(name ="humidity")
    var humidity = 0
    @ColumnInfo(name ="visibility")
    var visibility = 0
    @ColumnInfo(name ="windSpeed")
    var windSpeed = 0
    @ColumnInfo(name ="sunrise")
    var sunrise: Long = Date().time
    @ColumnInfo(name ="sunset")
    var sunset: Long = Date().time

    companion object{
        fun fromResponse(response : CityWeatherResponse) : CityDataEntity {
            val entity = CityDataEntity()
            entity.id = response.id
            entity.cityName = response.cityName
            entity.currentTemperature = response.main.temperature.roundToInt()
            entity.feelsLike = response.main.feelsLike.roundToInt()
            entity.weatherIcon = response.weather[0].icon
            entity.pressure = response.main.pressure
            entity.humidity = response.main.humidity
            entity.visibility = response.visibility
            entity.windSpeed = response.wind.speed
            entity.sunrise = response.sys.sunrise
            entity.sunset = response.sys.sunset
            return entity
        }
    }
}