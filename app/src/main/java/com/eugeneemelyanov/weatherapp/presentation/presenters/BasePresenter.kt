package com.eugeneemelyanov.weatherapp.presentation.presenters

import com.eugeneemelyanov.weatherapp.presentation.views.BaseView
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.CityListBaseView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

abstract class BasePresenter<T:BaseView> : MvpPresenter<T>() {
    var composites = CompositeDisposable()

    override fun detachView(view: T) {
        composites.clear()
        super.detachView(view)
    }

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
