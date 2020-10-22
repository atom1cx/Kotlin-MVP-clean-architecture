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
    fun bindItem(item: City){
        itemView.cityName.text = item.name
        itemView.temperature.text = itemView.context.getString(R.string.celsium_format).format(item.currentTemperature)
    }
}