package com.example.coinbase.presentation.home

sealed class HomeState {
    data class Loading(val loading: Boolean): HomeState()
    data class Error(val message: String): HomeState()
}