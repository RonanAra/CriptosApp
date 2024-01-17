package com.example.coinbase.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.repository.HomeRepository
import com.example.coinbase.utils.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private  val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var coins: List<CoinResponse> = listOf()

    fun getCoins() {
        viewModelScope.launchSuspendFun(
            blockToRun = { repository.getCoins() },
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

    fun filterList(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        _uiState.update {
            it.copy(list = coinsFiltered)
        }
    }

    fun dismissErrorDialog() {
        _uiState.update { currentState ->
            currentState.copy(showError = false)
        }
    }
}