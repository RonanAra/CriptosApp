package com.example.coinbase.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coinbase.domain.entity.CoinModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = WebSiteViewModel.MyViewModelFactory::class)
class WebSiteViewModel @AssistedInject constructor(
    @Assisted private val coinModel: CoinModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(WebSiteUiState())
    val uiState: StateFlow<WebSiteUiState> = _uiState.asStateFlow()

    fun load() {
        _uiState.update { currentState ->
            currentState.copy(
                url = coinModel.website,
                title = coinModel.name
            )
        }
    }

    @AssistedFactory
    interface MyViewModelFactory {
        fun create(coinModel: CoinModel): WebSiteViewModel
    }
}