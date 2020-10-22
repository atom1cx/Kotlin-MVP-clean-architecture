package com.eugeneemelyanov.weatherapp.model.repository

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.database.CityDetailsDatabaseInteractor
import com.eugeneemelyanov.weatherapp.domain.network.CityForecastNetworkInteractor
import com.eugeneemelyanov.weatherapp.domain.network.CityWeatherNerworkInteractor
import com.eugeneemelyanov.weatherapp.domain.network.GroupWeatherNetworkInteractor
import com.eugeneemelyanov.weatherapp.presentation.models.City
import com.eugeneemelyanov.weatherapp.presentation.models.CityDetailed
import com.eugeneemelyanov.weatherapp.presentation.models.Weather
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val databaseInteractor: CityDatabaseInteractor,
    private val detailsDbInteracotr: CityDetailsDatabaseInteractor,
    private val networkInteractor: CityWeatherNerworkInteractor,
    private val groupWeatherNetworkInteractor: GroupWeatherNetworkInteractor,
    private val cityForecastNetworkInteractor: CityForecastNetworkInteractor
) : CitiesRepository {

    override fun getCities(): Flowable<List<City>> {
        return databaseInteractor.getCities()
            .map { list -> list.map { item -> City.fromEntity(item) } }
    }

    override fun saveCity(cityName: String): Observable<City> {
        return networkInteractor.getCityWeather(cityName).toObservable().map { x ->
            City.fromEntity(x)
        }
    }

    override fun refreshCities(): Completable {
        val databaseObs = databaseInteractor.getCities().firstOrError()

        return databaseObs.map { cities ->
            cities.map { it.id }
        }.flatMap { ids -> groupWeatherNetworkInteractor.getGroupWeather(ids) }.toCompletable()
    }

    override fun getCityDetailed(id: Long): Flowable<CityDetailed> {
        return databaseInteractor.getCity(id).map { entity -> CityDetailed.fromEntity(entity) }
    }

    override fun getWeather(id: Long): Flowable<List<Weather>> {
        return detailsDbInteracotr.getCityWeather(id).map { entityList ->
            entityList.map { it ->
                Weather(
                    it.position,
                    it.currentTemperature,
                    it.weatherIcon,
                    LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(it.dt),
                        DateTimeUtils.toZoneId(TimeZone.getDefault()))
                )
            }
        }
    }

    override fun getCityForecast(name: String): Completable {
        return cityForecastNetworkInteractor.getCityForecast(name)
    }

}