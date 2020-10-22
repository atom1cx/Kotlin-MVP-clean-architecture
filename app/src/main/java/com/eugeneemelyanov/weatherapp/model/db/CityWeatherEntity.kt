package com.eugeneemelyanov.weatherapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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