package com.eugeneemelyanov.weatherapp.di

import androidx.navigation.NavController
import com.eugeneemelyanov.weatherapp.WeatherApplication
import com.eugeneemelyanov.weatherapp.presentation.MainActivity
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.eugeneemelyanov.weatherapp.presentation.navigation.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    fun provideNavHolder(): NavigationHolder{
        return WeatherApplication.INSTANCE.currentHolder
    }

    @Provides
    fun provideListRouter(impl: ListRouterImpl): ListRouter{
        return impl
    }

    @Provides
    fun provideDetailsRouter(impl: DetailsRouterImpl): DetailsRouter{
        return impl
    }

    @Provides
    fun provideNavigator(holder: NavigationHolder): Navigator{
        return holder.getNavigator()
    }
}