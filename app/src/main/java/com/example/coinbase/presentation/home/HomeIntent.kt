package com.example.coinbase.presentation.home

sealed class HomeIntent {
    data class FilterList(val name: String): HomeIntent()
    object LoadCoins: HomeIntent()
}