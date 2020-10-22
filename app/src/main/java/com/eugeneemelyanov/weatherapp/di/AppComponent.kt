package com.eugeneemelyanov.weatherapp.di

import android.content.Context
import com.eugeneemelyanov.weatherapp.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        CititesRepositoryModule::class,
        NavigationModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: WeatherApplication)

}