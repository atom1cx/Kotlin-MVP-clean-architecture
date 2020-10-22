package com.eugeneemelyanov.weatherapp.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.WeatherApplication
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.eugeneemelyanov.weatherapp.presentation.navigation.NavigationHolder
import com.eugeneemelyanov.weatherapp.presentation.navigation.Navigator
import com.eugeneemelyanov.weatherapp.presentation.navigation.NavigatorImpl
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class MainActivity @Inject constructor() : MvpAppCompatActivity(), HasSupportFragmentInjector, NavigationHolder {

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var nvg: Navigator

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        nvg = NavigatorImpl(navController)
        WeatherApplication.INSTANCE.currentHolder = this
    }

    override fun supportFragmentInjector() = fragmentInjector

    override fun onBackPressed() {
        nvg.getCurrentRouter().onBackPressed()
    }

    override fun getNavigator(): Navigator {
        return nvg
    }
}