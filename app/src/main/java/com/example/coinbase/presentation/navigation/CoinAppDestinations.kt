package com.example.coinbase.presentation.navigation

import com.example.coinbase.domain.entity.CoinModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val model: CoinModel)