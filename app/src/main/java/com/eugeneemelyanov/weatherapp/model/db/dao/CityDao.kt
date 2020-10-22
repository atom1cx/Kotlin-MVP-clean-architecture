package com.eugeneemelyanov.weatherapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import io.reactivex.Flowable

@Dao
interface CityDao {
    @Query("SELECT * FROM city_data")
    fun getCities(): Flowable<List<CityDataEntity>>

    @Query("SELECT * FROM city_data WHERE cityId=:id")
    fun getCity(id: Long): Flowable<CityDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(data : CityDataEntity)
}