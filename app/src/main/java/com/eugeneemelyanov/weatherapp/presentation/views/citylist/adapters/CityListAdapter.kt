package com.eugeneemelyanov.weatherapp.presentation.views.citylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eugeneemelyanov.weatherapp.presentation.models.City

class CityListAdapter: RecyclerView.Adapter<CityListItemViewHolder>() {

    private var items: List<City> = listOf()

    fun setItems(items: List<City>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListItemViewHolder {
        return CityListItemViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CityListItemViewHolder, position: Int) {
        items[position].let{
            holder.bindItem(it)
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun getItemCount(): Int {
        return items.size
    }
}