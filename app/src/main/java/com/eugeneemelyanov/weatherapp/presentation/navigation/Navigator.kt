package com.eugeneemelyanov.weatherapp.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.WeatherApplication
import com.eugeneemelyanov.weatherapp.presentation.navigation.Params.CITY_ID_PARAM
import com.eugeneemelyanov.weatherapp.presentation.navigation.Params.CITY_NAME_PARAM
import javax.inject.Inject

interface NavigationHolder{
    fun getNavigator() : Navigator
}

interface Navigator {
    fun navigateToCityDetails(cityId: Long, cityName: String)
    fun setUpRouter(router: BaseRouter)
    fun getCurrentRouter() : BaseRouter
    fun onBackPressed()
}

interface BaseRouter {
    fun onBackPressed()
}

interface ListRouter : BaseRouter, InitialRoute {
    fun navigateToCityDetails(cityId: Long, cityName: String)
}

interface InitialRoute

interface DetailsRouter : BaseRouter

class NavigatorImpl @Inject constructor(private val navController: NavController) : Navigator, ListRouter, DetailsRouter{

    lateinit var router: BaseRouter

    override fun navigateToCityDetails(cityId: Long, cityName: String) {
        val bundle = Bundle()
        bundle.putLong(CITY_ID_PARAM, cityId)
        bundle.putString(CITY_NAME_PARAM, cityName)
        navController.navigate(R.id.action_citiesListFragment_to_cityForecastFragment, bundle)
    }

    override fun setUpRouter(router: BaseRouter) {
        this.router = router
    }

    override fun getCurrentRouter(): BaseRouter {
        return router
    }

    override fun onBackPressed() {
        if(getCurrentRouter() !is InitialRoute){
            navController.popBackStack()
        }
    }
}

class DetailsRouterImpl @Inject constructor(private val navigator: Navigator) : DetailsRouter {
    init {
        navigator.setUpRouter(this)
    }
    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}

class ListRouterImpl @Inject constructor(private val navigator: Navigator) : ListRouter{
    init {
        navigator.setUpRouter(this)
    }
    override fun navigateToCityDetails(cityId: Long, cityName: String) {
        navigator.navigateToCityDetails(cityId, cityName)
    }

    override fun onBackPressed() {
        navigator.onBackPressed()
    }
}

object Params {
    const val CITY_ID_PARAM = "city_id"
    const val CITY_NAME_PARAM = "city_name"
}