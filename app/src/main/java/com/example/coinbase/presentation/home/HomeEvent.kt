package com.example.coinbase.presentation.home

sealed interface HomeEvent {
    data class SearchCoinByName(val name: String): HomeEvent
    data object LoadCoins: HomeEvent
}