package com.example.coinbase.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinModel(
    val color: String,
    val id: String,
    @SerialName("image_url")
    val imageUrl: String,
    val name: String,
    val symbol: String,
    val website: String,
)