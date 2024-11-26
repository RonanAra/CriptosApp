package com.example.coinbase.domain.usecase

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.repository.HomeRepository
import javax.inject.Inject

class GetCoinsUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): GetCoinsUseCase {
    override suspend fun invoke(): List<CoinModel> = repository.getCoins()
}