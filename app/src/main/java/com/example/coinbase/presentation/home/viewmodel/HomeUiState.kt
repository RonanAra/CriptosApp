package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.data.models.response.CoinResponse

data class HomeUiState(
    val list: List<CoinResponse> = listOf(),
    val loading: Boolean = false
)