package com.example.coinbase.common.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ConnectivityManagerHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val _connectivityState = MutableStateFlow(ConnectivityState())
    val connectivityState: StateFlow<ConnectivityState> get() = _connectivityState

    private val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _connectivityState.update { currentState ->
                currentState.copy(value = true)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _connectivityState.update { currentState ->
                currentState.copy(value = false)
            }
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

    fun unregisterCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

data class ConnectivityState(var value: Boolean? = null)