package com.example.shribhagwatgita.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class NetworkManager(context: Context): LiveData<Boolean>() {


    override fun onActive() {
        super.onActive()
        checkNetworkConnection()


    }
    private val connectManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest=NetworkRequest.Builder().apply{
        addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
    }.build()

    private val networkCallback= object: ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: android.net.Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }


        override fun onLost(network: android.net.Network) {
           super.onLost(network)
            postValue(false)
        }
    }

    private fun checkNetworkConnection() {
       if(connectManager.activeNetwork==null) {
           postValue(false)
       }
        connectManager.registerNetworkCallback(networkRequest,networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        releaseCheckingConnectivity()


    }

    private fun releaseCheckingConnectivity() {
        connectManager.unregisterNetworkCallback(networkCallback)
    }
}