package com.example.coinbase.presentation.navigation

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.utils.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
object Home

@Serializable
data class Detail(
    val model: CoinModel
) {
    companion object {
        val typeMap = mapOf(typeOf<CoinModel>() to serializableType<CoinModel>())
    }
}