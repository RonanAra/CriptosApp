package com.example.coinbase.common.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ConnectivityManagerHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val isConnected: Boolean get() = _isConnected.get()

    private var _isConnected: AtomicBoolean = AtomicBoolean(false)

    private val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _isConnected = AtomicBoolean(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _isConnected = AtomicBoolean(false)
        }
    }

    init {
        val networkRequest = NetworkRequest
            .Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        connectivityManager.registerNetworkCallback(
            networkRequest,
            networkCallback
        )
    }
}