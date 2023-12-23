package com.example.coinbase.presentation.home

import com.example.coinbase.data.models.response.CoinResponse

sealed class HomeState {
    data class Loading(val loading: Boolean): HomeState()
    data class Error(val message: String): HomeState()
    data class LoadCoins(val list: List<CoinResponse>): HomeState()
}