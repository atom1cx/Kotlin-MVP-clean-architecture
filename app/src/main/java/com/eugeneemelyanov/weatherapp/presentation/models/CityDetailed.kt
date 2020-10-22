package com.eugeneemelyanov.weatherapp.presentation.models

import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class CityDetailed(
    val id: Long,
    val name: String,
    val temperature: Int,
    val weatherIcon: String,
    val details: List<CityDetail>
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("HH:mm")
        fun fromEntity(entity: CityDataEntity): CityDetailed {
            val sunriseString = (if (entity.sunrise != 0L)
                LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(entity.sunrise),
                    DateTimeUtils.toZoneId(TimeZone.getDefault())
                )
            else LocalDateTime.now()).format(formatter)

            val sunsetString = (if (entity.sunset != 0L)
                LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(entity.sunrise),
                    DateTimeUtils.toZoneId(TimeZone.getDefault())
                )
            else LocalDateTime.now()).format(formatter)

            return CityDetailed(
                entity.id,
                entity.cityName,
                entity.currentTemperature,
                entity.weatherIcon,
                listOf(
                    CityDetail("Атмосферное давление", entity.pressure.toString()),
                    CityDetail("Влажность", entity.humidity.toString()),
                    CityDetail("Видимость", entity.visibility.toString()),
                    CityDetail("Восход солнца", sunriseString),
                    CityDetail("Закат", sunsetString)
                )
            )
        }
    }
}

