package com.eugeneemelyanov.weatherapp.presentation.models

import org.threeten.bp.LocalDateTime

data class Weather(
    val order: Int,
    val temperature: Int,
    val icon: String,
    val date: LocalDateTime
)