package com.eugeneemelyanov.weatherapp.presentation.presenters

import com.eugeneemelyanov.weatherapp.presentation.views.BaseView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

abstract class BasePresenter<T:BaseView> : MvpPresenter<T>() {
    var composites = CompositeDisposable()

    fun onStop() {composites.clear()}

    fun showError(error: String) {
        viewState?.showError(error)
    }

    fun showMessage(message: String){
        viewState?.showMessage(message)
    }

    fun onLoading(){
        viewState?.showLoader()
    }

    fun onLoadingFinished(){
        viewState?.hideLoader()
    }
}
