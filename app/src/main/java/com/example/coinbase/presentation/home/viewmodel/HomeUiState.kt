package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.domain.entity.CoinModel

sealed interface HomeUiState {
    data class FetchCoins(val list: List<CoinModel>) : HomeUiState
    data class Error(val message: String) : HomeUiState
    data object Loading : HomeUiState
}