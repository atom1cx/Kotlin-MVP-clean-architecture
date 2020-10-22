package com.eugeneemelyanov.weatherapp.di

import com.eugeneemelyanov.weatherapp.presentation.MainActivity
import com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.CityForecastFragment
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.CitiesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ContextModule {
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun listFragmentInjector(): CitiesListFragment

    @ContributesAndroidInjector
    abstract fun forecastFragment(): CityForecastFragment
}