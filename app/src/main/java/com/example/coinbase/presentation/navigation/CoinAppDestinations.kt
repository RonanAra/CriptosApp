package com.example.coinbase.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(
    val title: String,
    val url: String
)