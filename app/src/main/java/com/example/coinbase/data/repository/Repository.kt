package com.example.coinbase.data.repository

import com.example.coinbase.data.models.response.Data
import com.example.coinbase.data.service.CoinApi
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

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