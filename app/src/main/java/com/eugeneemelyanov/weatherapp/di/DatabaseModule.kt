package com.eugeneemelyanov.weatherapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.eugeneemelyanov.weatherapp.model.db.WeatherDatabase
import com.eugeneemelyanov.weatherapp.model.db.dao.CityDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) : WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "weatherdb").addCallback(object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO city_data VALUES (524901, 'Москва', 0, 0, '', 0, 0, 0, 0, 0, 0), (498817, 'Санкт-Петербург', 0, 0, '', 0, 0, 0, 0, 0, 0);")
            }
        }).build()
    }

    @Singleton
    @Provides
    fun providesCitiesDao(db: WeatherDatabase): CityDao {
        return db.cityDao()
    }

}