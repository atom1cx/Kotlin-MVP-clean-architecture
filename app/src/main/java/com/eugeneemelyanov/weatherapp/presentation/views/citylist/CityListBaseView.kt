package com.eugeneemelyanov.weatherapp.presentation.views.citylist

import com.eugeneemelyanov.weatherapp.presentation.models.City
import com.eugeneemelyanov.weatherapp.presentation.views.BaseView
import moxy.MvpView

interface CityListBaseView : MvpView, BaseView {
    fun updateData(cities: List<City>)
    fun addCity()
    fun clearEdit()
}