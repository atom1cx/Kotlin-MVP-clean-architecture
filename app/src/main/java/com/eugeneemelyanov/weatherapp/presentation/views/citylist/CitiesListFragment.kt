package com.eugeneemelyanov.weatherapp.presentation.views.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.presentation.models.City
import com.eugeneemelyanov.weatherapp.presentation.presenters.CitiesListPresenter
import com.eugeneemelyanov.weatherapp.presentation.views.BaseFragment
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.adapters.CityListAdapter
import kotlinx.android.synthetic.main.fragment_cities_list.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class CitiesListFragment : BaseFragment(), CityListBaseView {

    @Inject
    @InjectPresenter
    lateinit var currentPresenter: CitiesListPresenter

    @ProvidePresenter
    fun providePresenter() = currentPresenter

    private var adapter: CityListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            addCity()
        }

        setUpList()
    }

    private fun setUpList() {
        adapter = CityListAdapter(onItemClick = { id, name -> currentPresenter.navigateToCityDetails(id, name) })
        citiesList.layoutManager = LinearLayoutManager(context)
        citiesList.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager(context).orientation
            )
        )
        citiesList.adapter = adapter
    }

    override fun updateData(cities: List<City>) {
        adapter?.setItems(cities)
    }

    override fun addCity() {
        currentPresenter.addCity(cityNameEdit.text.toString())
    }

    override fun clearEdit() {
        cityNameEdit.setText("")
    }
}