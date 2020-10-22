package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast

import com.eugeneemelyanov.weatherapp.presentation.models.CityDetailed
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.eugeneemelyanov.weatherapp.presentation.views.BaseView
import moxy.MvpView

interface CityForecastBaseView : MvpView, BaseView {
    fun setCityDetails(detailed: CityDetailed)
    fun setCityForecast(forecast: List<Weather>)
    fun initialize()
}