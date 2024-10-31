package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.domain.entity.CoinModel

sealed interface HomeUiState {
    data class ListCoins(val list: List<CoinModel>) : HomeUiState
    data class Error(val message: String) : HomeUiState
    data object Loading : HomeUiState
}