package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.presentation.models.City
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import com.eugeneemelyanov.weatherapp.presentation.views.citylist.adapters.CityListItemViewHolder

class ForecastAdapter() : RecyclerView.Adapter<ForecastViewHolder>() {

    private var items: List<Weather> = listOf()

    fun setItems(items: List<Weather>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        items[position].let{
                weather ->
            holder.bindItem(weather)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}