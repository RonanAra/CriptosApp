package com.example.coinbase.api

import com.example.coinbase.model.Data
import javax.inject.Inject

interface HomeRepository {
    suspend fun getCoins(): List<Data>
    suspend fun filterList(text: String): List<Data>
}

class HomeRepositoryImpl @Inject constructor(
    private val service: CoinApi
): HomeRepository {

    override suspend fun getCoins(): List<Data> {
        return service.getCoinsList().data
    }

    override suspend fun filterList(text: String): List<Data> {
        val filteredList = service.getCoinsList().data.filter {
            it.name.lowercase().contains(text)
        }
        return filteredList
    }
}