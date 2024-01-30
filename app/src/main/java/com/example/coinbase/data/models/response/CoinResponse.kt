package com.example.coinbase.data.models.response

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val color: String,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val symbol: String,
    val website: String,
)