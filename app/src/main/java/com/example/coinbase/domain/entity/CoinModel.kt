package com.example.coinbase.domain.entity

import java.io.Serializable

data class CoinModel(
    val color: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val symbol: String,
    val website: String,
) : Serializable