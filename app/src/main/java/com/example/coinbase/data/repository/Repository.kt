package com.example.coinbase.data.repository

import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.data.service.CoinService
import com.example.coinbase.domain.repository.HomeRepository
import com.example.coinbase.utils.service
import retrofit2.Retrofit
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    retrofit: Retrofit.Builder
) : HomeRepository {
    private val service by service<CoinService>(retrofit)

    override suspend fun getCoins(): List<CoinResponse> {
        return service.getCoinsList().data
    }
}