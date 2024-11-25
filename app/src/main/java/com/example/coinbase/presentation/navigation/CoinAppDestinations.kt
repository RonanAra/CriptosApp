package com.example.coinbase.presentation.navigation

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.utils.extensions.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(
    val model: CoinModel
) {
    companion object {
        val typeMap = mapOf(typeOf<CoinModel>() to serializableType<CoinModel>())
    }
}