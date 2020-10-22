package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.R
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*
import kotlinx.android.synthetic.main.item_city.view.temperature
import kotlinx.android.synthetic.main.item_forecast_3hr.view.*
import org.threeten.bp.format.DateTimeFormatter

class ForecastViewHolder(inflater: LayoutInflater,
                         container: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_forecast_3hr, container, false)) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm")
    }
    fun bindItem(item: Weather){
        itemView.date.text = item.date.format(formatter)
        itemView.temperature.text = itemView.context.getString(R.string.celsium_format).format(item.temperature)
        Picasso.get().load(itemView.context.getString(R.string.forecast_icon_format).format(item.icon)).into(itemView.weatherIco)
    }
}