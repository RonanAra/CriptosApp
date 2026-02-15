package com.example.coinbase.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListCoinsResponse(
    @SerialName("data") val data: List<CoinResponse>
)