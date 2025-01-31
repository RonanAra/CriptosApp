package com.example.coinbase.presentation.navigation

import com.example.coinbase.domain.entity.CoinModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(
    val model: CoinModel
)