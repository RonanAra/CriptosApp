package com.example.coinbase.data.repository

import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.data.service.CoinApi
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val service: CoinApi
): HomeRepository {

    override suspend fun getCoins(): List<CoinResponse> {
        return service.getCoinsList().list
    }
}