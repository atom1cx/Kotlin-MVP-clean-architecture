package com.eugeneemelyanov.weatherapp.presentation.presenters

import com.eugeneemelyanov.weatherapp.model.remote.NetworkStateProvider
import com.eugeneemelyanov.weatherapp.model.repository.CitiesRepository
import com.eugeneemelyanov.weatherapp.presentation.navigation.DetailsRouter
import com.eugeneemelyanov.weatherapp.presentation.navigation.ListRouter
import com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.CityForecastBaseView
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.CityListBaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CityDetailsPresenter@Inject constructor(
    private val repository: CitiesRepository,
    private val networkStateProvider: NetworkStateProvider,
    private val router: DetailsRouter
) : BasePresenter<CityForecastBaseView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState?.initialize()
    }

    fun initWithCityIdName(id: Long, name: String){
        composites.add(
            repository.getCityDetailed(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { details -> viewState?.setCityDetails(details) },
                    { e -> showError(e.message ?: "Ошибка получения деталей города") })
        )

        composites.add(
            repository.getWeather(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weatherList -> viewState?.setCityForecast(weatherList) },
                    { e -> showError(e.message ?: "Ошибка получения погоды") })
        )

        composites.add(networkStateProvider.isNetworkAvailable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ isConnected -> if(isConnected) refreshCityData(name)},
                {e -> showError(e.message?:"Ошибка получения состояния сети")}))

    }

    fun refreshCityData(name: String){
        composites.add(
            repository.getCityForecast(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { e -> showError(e.message ?: "Ошибка обновления городов") })
        )
    }

}