package com.eugeneemelyanov.weatherapp.domain

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.db.CityWeatherEntity
import com.eugeneemelyanov.weatherapp.model.remote.models.CityForecastResponse

data class ForecastMappableEntity(
    val weather: List<CityWeatherEntity>,
    val cityInfo: CityDataEntity
) {
    companion object {
        fun fromResponse(response: CityForecastResponse): ForecastMappableEntity {
            return ForecastMappableEntity(
                weather = CityForecastResponse.mapToWeatherList(response),
                cityInfo = CityForecastResponse.mapToCityEntity(response)
            )
        }
    }
}

/*
@Entity(tableName = "city_weather", primaryKeys = ["cityId", "position"])
class CityWeatherEntity {
    @ColumnInfo(name = "cityId")
    var id: Long = 0
    @ColumnInfo(name = "currentTemperature")
    var currentTemperature: Int = 0
    @ColumnInfo(name = "dt")
    var dt: Long = 0
    @ColumnInfo(name = "position")
    var position: Int = 0
    @ColumnInfo(name = "weatherIcon")
    var weatherIcon: String = ""
}
 */