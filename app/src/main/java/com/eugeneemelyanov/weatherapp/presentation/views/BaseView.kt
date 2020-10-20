package com.eugeneemelyanov.weatherapp.presentation.views

import moxy.MvpView

interface BaseView : MvpView {
    fun showError(error: String)
    fun showMessage(message: String)
    fun showLoader()
    fun hideLoader()
}