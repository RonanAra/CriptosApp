package com.example.coinbase.presentation.navigation

import com.example.coinbase.domain.entity.CoinModel
import kotlinx.serialization.Serializable

sealed interface CoinAppDestinations {

    @Serializable
    object HomeRoute : CoinAppDestinations

    @Serializable
    data class DetailRoute(val model: CoinModel) : CoinAppDestinations
}