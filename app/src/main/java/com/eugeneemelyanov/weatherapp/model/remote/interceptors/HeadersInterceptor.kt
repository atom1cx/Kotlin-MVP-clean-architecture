package com.eugeneemelyanov.weatherapp.model.remote.interceptors

import com.eugeneemelyanov.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val LANG= "RU"
        private const val UNITS = "metric"

        private const val LANG_HEADER = "lang"
        private const val UNITS_HEADER = "units"
        private const val API_KEY_HEADER ="appid"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        var originalUrl = originalRequest.url()

        val modifiedUrl = originalUrl.newBuilder()
            .addQueryParameter(LANG_HEADER, LANG)
            .addQueryParameter(UNITS_HEADER, UNITS)
            .addQueryParameter(API_KEY_HEADER, BuildConfig.API_KEY)
            .build()

        val builder = originalRequest.newBuilder()
            .url(modifiedUrl)

        val modifiedRequest = builder.build()
        return chain.proceed(modifiedRequest)
    }

}