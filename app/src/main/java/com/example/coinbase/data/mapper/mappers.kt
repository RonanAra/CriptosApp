package com.example.coinbase.data.mapper

import com.example.coinbase.data.models.response.ListCoinsResponse
import com.example.coinbase.domain.entity.CoinModel

fun ListCoinsResponse.toDomain(): List<CoinModel> {
    return this.data.map { response ->
        with(response) {
            CoinModel(
                color = color,
                id = id,
                imageUrl = imageUrl,
                name = name,
                symbol = symbol,
                website = website
            )
        }
    }
}