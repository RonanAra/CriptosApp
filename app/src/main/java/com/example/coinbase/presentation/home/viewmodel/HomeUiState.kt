package com.example.coinbase.presentation.home.viewmodel

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.utils.AppConstants

data class HomeUiState(
    val coins: List<CoinModel>? = null,
    val errorMessage: String? = null,
    val searchText: String = AppConstants.EMPTY_STRING,
    val isRefreshing: Boolean = false
)