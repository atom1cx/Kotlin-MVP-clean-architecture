package com.eugeneemelyanov.weatherapp.di

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractorImpl
import com.eugeneemelyanov.weatherapp.domain.network.CityWeatherNerworkInteractor
import com.eugeneemelyanov.weatherapp.domain.network.CityWeatherNetworkInteractorImpl
import com.eugeneemelyanov.weatherapp.domain.network.GroupWeatherNetworkInteractor
import com.eugeneemelyanov.weatherapp.domain.network.GroupWeatherNetworkInteractorImpl
import com.eugeneemelyanov.weatherapp.model.repository.CitiesRepository
import com.eugeneemelyanov.weatherapp.model.repository.CitiesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CititesRepositoryModule {

    @Provides
    @Singleton
    fun provideCityWeatherNetworkInteractor(impl: CityWeatherNetworkInteractorImpl): CityWeatherNerworkInteractor {
        return impl
    }

    @Provides
    fun provideCityDatabaseInteractor(impl: CityDatabaseInteractorImpl): CityDatabaseInteractor {
        return impl
    }

    @Provides
    fun provideGroupWeatherNetworkInteractor(impl: GroupWeatherNetworkInteractorImpl): GroupWeatherNetworkInteractor {
        return impl
    }

    @Provides
    @Singleton
    fun provideCitiesRepository(
        databaseInteractor: CityDatabaseInteractor,
        networkInteractor: CityWeatherNerworkInteractor,
        groupWeatherNetworkInteractor: GroupWeatherNetworkInteractor
    ): CitiesRepository {
        return CitiesRepositoryImpl(
            databaseInteractor,
            networkInteractor,
            groupWeatherNetworkInteractor
        )
    }
}