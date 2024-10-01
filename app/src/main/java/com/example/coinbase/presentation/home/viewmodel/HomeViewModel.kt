package com.example.coinbase.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.common.connectivity.ConnectivityManagerHelper
import com.example.coinbase.common.connectivity.ConnectivityState
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.utils.AppConstants
import com.example.coinbase.utils.CoroutinesConstants
import com.example.coinbase.utils.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinsUseCase: GetCoinsUseCase,
    private val connectivityHelper: ConnectivityManagerHelper
) : ViewModel() {
    val isConnected: StateFlow<ConnectivityState> = connectivityHelper.connectivityState

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
        .onStart { getCoins() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(AppConstants.STOP_TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    private var coins: List<CoinModel> = listOf()

    fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { coinsUseCase() },
            onLoading = { loading ->
                _uiState.update { uiState ->
                    uiState.copy(loading = loading)
                }
            },
            onSuccess = { response ->
                _uiState.update {
                    it.copy(list = response)
                }
                coins = response
            },
            onError = { error ->
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = error.message.orEmpty(),
                        showError = true
                    )
                }
            }
        )
    }

    fun onSearchCoinByName(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        _uiState.update {
            it.copy(
                list = coinsFiltered,
                searchInputText = text
            )
        }
    }

    fun dismissErrorDialog() {
        _uiState.update { currentState ->
            currentState.copy(showError = false)
        }
    }

    fun showErrorUnknownHost() {
        _uiState.update { currentState ->
            currentState.copy(
                showError = true,
                errorMessage = CoroutinesConstants.ERROR_UNKNOWN_HOST
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        connectivityHelper.unregisterCallback()
    }
}