package com.example.coinbase.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.presentation.home.HomeEvent
import com.example.coinbase.presentation.home.HomeUiEvent
import com.example.coinbase.utils.AppConstants.STOP_TIMEOUT_MILLIS
import com.example.coinbase.utils.extensions.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
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

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
        .onStart { getCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchCoinByName -> onSearchCoinByName(event.name)
            is HomeEvent.OnClickCardItem -> navigateToCoinWebsite(event.item)
            HomeEvent.LoadCoins -> getCoins()
            HomeEvent.HideErrorDialog -> hideErrorDialog()
        }
    }

    private fun navigateToCoinWebsite(item: CoinModel) {
        viewModelScope.launch {
            eventChannel.send(HomeUiEvent.UiToWebSite(item))
        }
    }

    private fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { coinsUseCase() },
            onLoading = { loading ->
                _uiState.update { state ->
                    state.copy(isRefreshing = loading)
                }
            },
            onSuccess = { coinModelList ->
                _uiState.update { state ->
                    state.copy(coins = coinModelList)
                }
                coins = coinModelList
            },
            onError = { error ->
                _uiState.update { state ->
                    state.copy(errorMessage = error.message)
                }
            }
        )
    }

    private fun onSearchCoinByName(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        onSearchTextChange(text)
        _uiState.update { state ->
            state.copy(coins = coinsFiltered)
        }
    }

    private fun onSearchTextChange(text: String) {
        _uiState.update { state ->
            state.copy(searchText = text)
        }
    }

    private fun hideErrorDialog() {
        _uiState.update { state ->
            state.copy(errorMessage = null)
        }
    }
}