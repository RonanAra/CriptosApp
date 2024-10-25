package com.example.coinbase.data.intercptor

import com.example.coinbase.common.connectivity.ConnectivityManagerHelper
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor(
    private val connectivityManagerHelper: ConnectivityManagerHelper
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectivityManagerHelper.isConnected) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailable()
        }
    }
}