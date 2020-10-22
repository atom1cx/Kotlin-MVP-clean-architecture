package com.eugeneemelyanov.weatherapp.di

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractorImpl
import com.eugeneemelyanov.weatherapp.domain.database.CityDetailsDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.database.CityDetailsDatabaseInteractorImpl
import com.eugeneemelyanov.weatherapp.domain.network.*
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
    fun provideCityDetailDbInteractor(impl: CityDetailsDatabaseInteractorImpl): CityDetailsDatabaseInteractor {
        return impl
    }

    @Provides
    fun provideCityForecastNetworkInteractor(impl: CityForecastNetworkInteractorImpl): CityForecastNetworkInteractor {
        return impl
    }

    @Provides
    @Singleton
    fun provideCitiesRepository(
        databaseInteractor: CityDatabaseInteractor,
        detailsDbInteractor: CityDetailsDatabaseInteractor,
        networkInteractor: CityWeatherNerworkInteractor,
        groupWeatherNetworkInteractor: GroupWeatherNetworkInteractor,
        cityForecastNetworkInteractor: CityForecastNetworkInteractor
    ): CitiesRepository {
        return CitiesRepositoryImpl(
            databaseInteractor,
            detailsDbInteractor,
            networkInteractor,
            groupWeatherNetworkInteractor,
            cityForecastNetworkInteractor
        )
    }
}