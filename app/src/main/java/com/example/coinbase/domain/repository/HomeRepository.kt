package com.example.coinbase.domain.repository

import com.example.coinbase.data.models.response.CoinResponse

interface HomeRepository {
    suspend fun getCoins(): List<CoinResponse>
}