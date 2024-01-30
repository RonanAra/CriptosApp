package com.example.coinbase.data.repository

import com.example.coinbase.data.mapper.toItemCoinModel
import com.example.coinbase.data.service.CoinService
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val service: CoinService
): HomeRepository {

    override suspend fun getCoins(): List<CoinModel> {
        return service.getCoinsList().data.map { it.toItemCoinModel() }
    }
}