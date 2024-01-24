package com.example.coinbase.data.models.response

import com.example.coinbase.domain.entity.CoinModel
import com.google.gson.annotations.SerializedName

data class CoinResponse(
    val color: String,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val symbol: String,
    val website: String,
) {
    fun toModel(): CoinModel {
        return CoinModel(
            color = color,
            id = id,
            imageUrl = imageUrl,
            name = name,
            symbol = symbol,
            website = website
        )
    }
}