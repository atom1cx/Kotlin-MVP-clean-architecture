package com.eugeneemelyanov.weatherapp.model.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

interface NetworkStateProvider {
    fun isNetworkAvailable(): Observable<Boolean>
    val isAvailable : Boolean
}

class NetworkStateProviderImpl @Inject constructor(val context: Context) : NetworkStateProvider{

    private val bsConnected = BehaviorSubject.create<Boolean>()

    init {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    if(bsConnected.value != true){
                        bsConnected.onNext(true)
                    }
                }

                override fun onLost(network: Network) {
                    if(bsConnected.value != false){
                        bsConnected.onNext(false)
                    }
                }
            })
    }

    override fun isNetworkAvailable(): Observable<Boolean> {
        return bsConnected
    }

    override val isAvailable: Boolean
        get() = bsConnected.value
}

