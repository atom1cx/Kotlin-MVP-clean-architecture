package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetail
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetailed
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.eugeneemelyanov.weatherapp.presentation.navigation.Params.CITY_ID_PARAM
import com.eugeneemelyanov.weatherapp.presentation.navigation.Params.CITY_NAME_PARAM
import com.eugeneemelyanov.weatherapp.presentation.presenters.CitiesListPresenter
import com.eugeneemelyanov.weatherapp.presentation.presenters.CityDetailsPresenter
import com.eugeneemelyanov.weatherapp.presentation.views.BaseFragment
import com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters.CityDetailsAdapter
import com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters.ForecastAdapter
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.CitiesListFragment
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.adapters.CityListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cities_list.*
import kotlinx.android.synthetic.main.fragment_city_forecast.*
import kotlinx.android.synthetic.main.item_forecast_3hr.view.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CityForecastFragment : BaseFragment(), CityForecastBaseView {
    private var cityIdExtra: Long = -1
    private var cityNameExtra = ""

    @Inject
    @InjectPresenter
    lateinit var currentPresenter: CityDetailsPresenter

    @ProvidePresenter
    fun providePresenter() = currentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityIdExtra = it.getLong(CITY_ID_PARAM, -1)
        }

        arguments?.let {
            cityNameExtra = it.getString(CITY_NAME_PARAM, "")
        }
    }

    private var detailsAdapter: CityDetailsAdapter? = null
    private var forecastAdapter: ForecastAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDetailsAdapter()
        setUpForecastAdapter()
    }

    private fun setUpDetailsAdapter(){
        detailsAdapter = CityDetailsAdapter()
        detailsList.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager(context).orientation
            )
        )
        detailsList.adapter = detailsAdapter
    }

    private fun setUpForecastAdapter(){
        forecastAdapter = ForecastAdapter()
        forecastList.adapter = forecastAdapter
    }

    override fun setCityDetails(detailed: CityDetailed) {
        detailsAdapter?.setItems(detailed.details)
        context?.let{ctx ->
            Picasso.get().load(ctx.getString(R.string.forecast_icon_format).format(detailed.weatherIcon)).into(currentTimeImg)
            cityCurrentTemperature.text = ctx.getString(R.string.celsium_format).format(detailed.temperature)
        }
        cityName.text = detailed.name
    }

    override fun setCityForecast(forecast: List<Weather>) {
        forecastAdapter?.setItems(forecast)
    }

    override fun initialize() {
        currentPresenter.initWithCityIdName(cityIdExtra, cityNameExtra)
    }
}