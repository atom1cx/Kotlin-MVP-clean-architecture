package com.eugeneemelyanov.weatherapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eugeneemelyanov.weatherapp.model.db.dao.CityDao

@Database(entities = [CityDataEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityDao() : CityDao
}