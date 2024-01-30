package com.example.coinbase.data.mapper

import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.entity.CoinModel

fun CoinResponse.toItemCoinModel(): CoinModel {
    return CoinModel(
        color = color,
        id = id,
        imageUrl = imageUrl,
        name = name,
        symbol = symbol,
        website = website
    )
}