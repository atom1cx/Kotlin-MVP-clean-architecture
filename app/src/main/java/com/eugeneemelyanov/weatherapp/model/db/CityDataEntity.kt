package com.eugeneemelyanov.weatherapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eugeneemelyanov.weatherapp.model.remote.models.CityWeatherResponse
import kotlin.math.roundToInt

@Entity(tableName = "city_data")
class CityDataEntity() {

    @PrimaryKey
    @ColumnInfo(name = "cityId")
    var id: Long = 0
    @ColumnInfo(name = "cityName")
    var cityName: String = ""
    @ColumnInfo(name = "currentTemperature")
    var currentTemperature: Int = 0


    companion object{
        fun fromResponse(response : CityWeatherResponse) : CityDataEntity {
            val entity = CityDataEntity()
            entity.id = response.id
            entity.cityName = response.cityName
            entity.currentTemperature = response.main.temperature.roundToInt()
            return entity
        }

        fun fromParams(id: Long, cityName : String) : CityDataEntity{
            val entity = CityDataEntity()
            entity.id = id
            entity.cityName = cityName
            return entity
        }
    }

}