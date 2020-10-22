package com.eugeneemelyanov.weatherapp.model.remote.models

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Int
)

data class WindFloat(
    @SerializedName("speed")
    val speed: Float
)