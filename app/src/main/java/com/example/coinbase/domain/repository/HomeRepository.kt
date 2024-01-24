package com.example.coinbase.domain.repository

import com.example.coinbase.domain.entity.CoinModel

interface HomeRepository {
    suspend fun getCoins(): List<CoinModel>
}