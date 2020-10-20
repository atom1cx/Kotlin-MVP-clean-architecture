package com.eugeneemelyanov.weatherapp.presentation.models

import org.threeten.bp.LocalDateTime

data class CityDetailed(
    val name: String,
    val temperature: Int,
    val feelsLike: Int,
    val weatherIcon: String,
    val pressure: Int,
    val humidity: Int,
    val visibilityMeters: Int,
    val windSpeed: Int,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime
)

