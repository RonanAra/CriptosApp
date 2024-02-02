package com.example.coinbase.domain.usecase

import com.example.coinbase.domain.entity.CoinModel

interface GetCoinsUseCase {
    suspend operator fun invoke(): List<CoinModel>
}