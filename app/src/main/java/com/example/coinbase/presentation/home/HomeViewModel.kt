package com.example.coinbase.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.coinbase.base.BaseViewModel
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.repository.HomeRepository
import com.example.coinbase.utils.launchSuspendFun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel<HomeIntent, HomeState>() {

    private  val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get () = _uiState

    private var coins: List<CoinResponse> = listOf()

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadCoins -> getCoins()
            is HomeIntent.FilterList -> filterList(intent.name)
        }
    }

    private fun getCoins() {
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
            onError = {
                _state.value = HomeState.Error(it.message.orEmpty())
            }
        )
    }

    private fun filterList(text: String) {
        val coinsFiltered = coins.filter {
            it.name.lowercase().contains(text.lowercase())
        }
        _uiState.update {
            it.copy(list = coinsFiltered)
        }
    }
}