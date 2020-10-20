package com.eugeneemelyanov.weatherapp.presentation.models

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity

data class City(val id: Long, val name: String, val currentTemperature: Int){
    companion object {
        fun fromEntity(entity : CityDataEntity) : City{
            return City(entity.id, entity.cityName, entity.currentTemperature)
        }
    }
}