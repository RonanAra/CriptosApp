package com.example.coinbase.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.presentation.home.HomeEvent
import com.example.coinbase.presentation.home.HomeUiEvent
import com.example.coinbase.utils.AppConstants.EMPTY_STRING
import com.example.coinbase.utils.AppConstants.STOP_TIMEOUT_MILLIS
import com.example.coinbase.utils.extensions.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private var coins: List<CoinModel> = listOf()
    var searchText by mutableStateOf(EMPTY_STRING)
        private set

    private val _uiEvent = MutableSharedFlow<HomeUiEvent>()
    val uiEvent: SharedFlow<HomeUiEvent> = _uiEvent.asSharedFlow()

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState
        .onStart { getCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = HomeUiState.Loading
        )

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchCoinByName -> onSearchCoinByName(event.name)
            is HomeEvent.OnClickCardItem -> navigateToCoinWebsite(event.item)
            HomeEvent.LoadCoins -> getCoins()
        }
    }

    private fun navigateToCoinWebsite(item: CoinModel) {
        viewModelScope.launch {
            _uiEvent.emit(HomeUiEvent.UiToWebSite(item))
        }
    }

    private fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { coinsUseCase() },
            onSuccess = { response ->
                HomeUiState.FetchCoins(response).updateUiState()
                coins = response
            },
            onError = { error ->
                HomeUiState.Error(error.message.orEmpty()).updateUiState()
            }
        )
    }

    private fun onSearchCoinByName(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        onSearchTextChange(text)
        HomeUiState.FetchCoins(coinsFiltered).updateUiState()
    }

    private fun onSearchTextChange(newText: String) {
        searchText = newText
    }

    private fun HomeUiState.updateUiState() = _uiState.update { this }
}