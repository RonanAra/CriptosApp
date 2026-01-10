package com.example.coinbase.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.presentation.home.HomeEvent
import com.example.coinbase.presentation.home.HomeUiEvent
import com.example.coinbase.utils.extensions.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private var coins: List<CoinModel> = listOf()

    private val eventChannel = Channel<HomeUiEvent>(Channel.BUFFERED)
    val events: Flow<HomeUiEvent> = eventChannel.receiveAsFlow()

    private val _uiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState.Uninitialized)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchCoinByName -> onSearchCoinByName(event.name)
            is HomeEvent.OnClickCardItem -> navigateToCoinWebsite(event.item)
            HomeEvent.LoadCoins, HomeEvent.Initialize -> getCoins()
        }
    }

    private fun navigateToCoinWebsite(item: CoinModel) {
        viewModelScope.launch {
            eventChannel.send(HomeUiEvent.NavigateToCoinWebSite(item))
        }
    }

    private fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { coinsUseCase() },
            onLoading = {
                _uiState.update {
                    HomeUiState.Loading
                }
            },
            onSuccess = { coinModelList ->
                _uiState.update {
                    HomeUiState.Loaded(coins = coinModelList)
                }
                coins = coinModelList
            },
            onError = { error ->
                _uiState.update {
                    HomeUiState.Error(error.message.orEmpty())
                }
            }
        )
    }

    private fun onSearchCoinByName(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        _uiState.update { state ->
            (state as? HomeUiState.Loaded)?.copy(
                searchText = text, coins = coinsFiltered
            ) ?: state
        }
    }
}