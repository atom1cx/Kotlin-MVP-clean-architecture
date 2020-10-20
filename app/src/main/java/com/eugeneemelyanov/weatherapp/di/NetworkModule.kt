package com.eugeneemelyanov.weatherapp.di

import android.content.Context
import com.eugeneemelyanov.weatherapp.BuildConfig
import com.eugeneemelyanov.weatherapp.model.remote.*
import com.eugeneemelyanov.weatherapp.model.remote.interceptors.HeadersInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.ZonedDateTime
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    @Named("apiClient")

    fun provideOkHttpClient(headersInterceptor: HeadersInterceptor): OkHttpClient {
        var builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(ZonedDateTime::class.java,
                ZonedDateTimeDeserializer()
            )
            .create()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideInterceptor() =
        HeadersInterceptor()

    @Singleton
    @Provides
    @Named("openweathermap")
    fun provideRetrofit(
        @Named("apiClient") client: OkHttpClient,
        converterFactory: Converter.Factory
    ) =
        Retrofit.Builder()
            .baseUrl(Configuration.OPEN_WEATHER_MAP_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideApi(@Named("openweathermap") retrofit: Retrofit) = retrofit.create(OpenWeatherMapApi::class.java)

    @Singleton
    @Provides
    fun provideNetworkStateProvider(context: Context): NetworkStateProvider = NetworkStateProviderImpl(context)
}