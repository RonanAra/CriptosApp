package com.example.coinbase.data.datasource

import com.example.coinbase.data.models.response.ListCoinsResponse
import com.example.coinbase.data.network.service.CoinService
import com.example.coinbase.utils.service
import retrofit2.Retrofit
import javax.inject.Inject

interface HomeDataSource {
    suspend fun getCoins(): ListCoinsResponse
}

class HomeDataSourceRemoteImpl @Inject constructor(
    retrofit: Retrofit.Builder
) : HomeDataSource {
    private val service by service<CoinService>(retrofit)

    override suspend fun getCoins(): ListCoinsResponse {
        return service.getCoinsList()
    }
}