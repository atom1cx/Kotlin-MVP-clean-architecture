package com.eugeneemelyanov.weatherapp.presentation.views.cityforecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetail
import com.eugeneemelyanov.weatherapp.presentation.models.Weather

class CityDetailsAdapter : RecyclerView.Adapter<CityDetailViewHolder>() {

    private var items: List<CityDetail> = listOf()

    fun setItems(items: List<CityDetail>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDetailViewHolder {
        return CityDetailViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CityDetailViewHolder, position: Int) {
        items[position].let{
                detail ->
            holder.bindItem(detail)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}