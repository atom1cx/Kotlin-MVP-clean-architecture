package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetail
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*
import kotlinx.android.synthetic.main.item_forecast_3hr.view.*
import kotlinx.android.synthetic.main.item_forecast_3hr.view.temperature
import kotlinx.android.synthetic.main.item_forecast_detail.view.*

class CityDetailViewHolder (inflater: LayoutInflater,
                            container: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_forecast_detail, container, false)){
    fun bindItem(item: CityDetail){
        itemView.itemName.text = item.name
        itemView.itemData.text = item.value
    }
}