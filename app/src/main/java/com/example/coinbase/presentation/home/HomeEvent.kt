package com.example.coinbase.presentation.home

import com.example.coinbase.domain.entity.CoinModel

sealed interface HomeEvent {
    data class SearchCoinByName(val name: String) : HomeEvent
    data class OnClickCardItem(val item: CoinModel) : HomeEvent
    data object LoadCoins : HomeEvent
}

sealed interface HomeNavigationEvent {
    data class NavigationToWebSite(val item: CoinModel) : HomeNavigationEvent
}