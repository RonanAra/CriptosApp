package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.domain.entity.CoinModel

data class HomeUiState(
    val list: List<CoinModel> = listOf(),
    val loading: Boolean = false,
    val showError: Boolean = false,
    val errorMessage: String = ""
)