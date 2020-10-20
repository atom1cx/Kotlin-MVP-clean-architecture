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
        /*
        val MIGRATION_INITIAL_CITIES = object : Migration(0, 1){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("INSERT INTO city_data(`cityId`,`cityName`,`currentTemperature`) VALUES (524901, `Москва`, 0), (498817, `Санкт-Петербург`, 0)")
            }
        }*/

        return Room.databaseBuilder(context, WeatherDatabase::class.java, "weatherdb").addCallback(object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO city_data VALUES (524901, 'Москва', 0), (498817, 'Санкт-Петербург', 0);")
            }
        }).build()
    }
//, (498817, Spb, 0)
    @Singleton
    @Provides
    fun providesCitiesDao(db: WeatherDatabase): CityDao {
        return db.cityDao()
    }

}