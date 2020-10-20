package com.eugeneemelyanov.weatherapp.presentation.presenters

import com.eugeneemelyanov.weatherapp.model.repository.CitiesRepository
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.CityListBaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CitiesListPresenter @Inject constructor(
    private val repository: CitiesRepository
) : BasePresenter<CityListBaseView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        composites.add(
            repository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { cities -> viewState?.updateData(cities) },
                    { e -> showError(e.message ?: "Ошибка получения списка городов") })
        )
    }

    fun addCity(cityName: String) {
        if (cityName.isNotEmpty()) {
            composites.add(
                repository.saveCity(cityName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ city ->
                        showMessage("Город ${city.name} добавлен")
                        viewState?.clearEdit()
                    }, { e -> showError(e.message ?: "Ошибка добавления города") })
            )
        } else {
            showError("Введите имя города")
        }
    }
}