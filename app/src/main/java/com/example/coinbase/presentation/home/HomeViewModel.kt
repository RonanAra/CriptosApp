package com.example.coinbase.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.coinbase.base.BaseViewModel
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.repository.HomeRepository
import com.example.coinbase.utils.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel<HomeIntent, HomeState>() {
    private var coins: List<CoinResponse> = listOf()

    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.LoadCoins -> getCoins()
            is HomeIntent.FilterList -> filterList(intent.name)
        }
    }

    private fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { repository.getCoins() },
            onLoading = { loading ->
               _state.value = HomeState.Loading(loading)
            },
            onSuccess = { response ->
                _state.value = HomeState.LoadCoins(response)
                coins = response
            },
            onError = {
                _state.value = HomeState.Error(it.message.orEmpty())
            }
        )
    }

    private fun filterList(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
       _state.value = HomeState.LoadCoins(coinsFiltered)
    }
}