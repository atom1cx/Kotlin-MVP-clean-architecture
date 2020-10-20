package com.eugeneemelyanov.weatherapp.presentation.views.citylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.presentation.models.City
import kotlinx.android.synthetic.main.item_city.view.*

class CityListItemViewHolder(inflater: LayoutInflater,
                             container: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city, container, false)) {
    var id = 0
    fun bindItem(item: City){
        id = 0
        itemView.cityName.text = item.name
        itemView.temperature.text = item.currentTemperature.toString()
    }
}