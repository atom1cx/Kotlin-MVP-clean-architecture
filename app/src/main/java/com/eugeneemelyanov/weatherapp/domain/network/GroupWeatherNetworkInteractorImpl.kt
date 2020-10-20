package com.eugeneemelyanov.weatherapp.domain.network

import com.eugeneemelyanov.weatherapp.domain.database.CityDatabaseInteractor
import com.eugeneemelyanov.weatherapp.model.db.CityDataEntity
import com.eugeneemelyanov.weatherapp.model.remote.OpenWeatherMapApi
import com.eugeneemelyanov.weatherapp.model.remote.models.GroupWeatherResponse
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class GroupWeatherNetworkInteractorImpl @Inject constructor(
    val apiService: OpenWeatherMapApi,
    private val databaseInteractor: CityDatabaseInteractor
) : GroupWeatherNetworkInteractor {
    override fun getGroupWeather(ids: List<Long>): Single<List<CityDataEntity>> {
        val citiesStrings = ids.chunked(10).map { x -> x.joinToString(separator = ",") }
        return Observable.fromIterable(object : Iterable<GroupWeatherResponse> {
            var itemIndex = 0

            override fun iterator(): Iterator<GroupWeatherResponse> {
                return object : MutableIterator<GroupWeatherResponse> {
                    override fun hasNext(): Boolean {
                        return itemIndex < citiesStrings.size
                    }

                    override fun next(): GroupWeatherResponse {
                        val response =
                            apiService.getGroupWeather(citiesStrings[itemIndex]).blockingGet()
                        itemIndex++
                        return response
                    }

                    override fun remove() {}
                }
            }
        }).map { response ->
            response.list.map { item ->
                CityDataEntity.fromResponse(item)
            }
        }.single(listOf()).doOnSuccess { data ->
            databaseInteractor.saveCities(data)
        }.doOnError { error ->
            Timber.d(error)
        }
    }
}