package com.example.coinbase.domain.repository

import com.example.coinbase.data.models.response.Data

interface HomeRepository {
    suspend fun getCoins(): List<Data>
}