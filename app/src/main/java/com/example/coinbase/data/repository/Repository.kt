package com.example.coinbase.data.repository

import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.data.service.CoinService
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.mapper.CoinListMapperToModel
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val service: CoinService
): HomeRepository {
    override suspend fun getCoins(): List<CoinResponse> {
        return service.getCoinsList().data
    }
}