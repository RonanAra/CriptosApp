package com.example.coinbase.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinResponse(
    @SerialName("color") val color: String,
    @SerialName("id") val id: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("website") val website: String,
)