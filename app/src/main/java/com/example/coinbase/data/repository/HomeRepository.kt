package com.example.coinbase.data.repository

import com.example.coinbase.data.datasource.HomeDataSource
import com.example.coinbase.data.mapper.toDomain
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getCoins(): List<CoinModel> {
        return dataSource.getCoins().toDomain()
    }
}