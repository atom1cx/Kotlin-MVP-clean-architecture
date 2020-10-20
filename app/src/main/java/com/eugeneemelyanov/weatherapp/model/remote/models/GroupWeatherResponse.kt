package com.eugeneemelyanov.weatherapp.model.remote.models

import com.google.gson.annotations.SerializedName

data class GroupWeatherResponse(
    @SerializedName("list")
    val list : List<CityWeatherResponse>
)