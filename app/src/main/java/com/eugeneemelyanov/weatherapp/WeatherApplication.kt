package com.eugeneemelyanov.weatherapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.eugeneemelyanov.weatherapp.di.AppInjector
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class WeatherApplication : Application(), HasActivityInjector {

    companion object {
        @JvmStatic lateinit var INSTANCE: WeatherApplication
        var AppContext: Context? = null
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector() = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppInjector.inject(this)
        AppContext = this

        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }

        AndroidThreeTen.init(this)
    }
}