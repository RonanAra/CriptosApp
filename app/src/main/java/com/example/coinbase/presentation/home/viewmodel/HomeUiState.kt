package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.utils.AppConstants

sealed interface HomeUiState {
    data object Uninitialized : HomeUiState
    data object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Loaded(
        val coins: List<CoinModel>,
        val searchText: String = AppConstants.EMPTY_STRING
    ) : HomeUiState
}
