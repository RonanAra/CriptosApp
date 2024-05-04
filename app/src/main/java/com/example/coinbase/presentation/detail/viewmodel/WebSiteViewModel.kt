package com.example.coinbase.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
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
    @Assisted("url") private val url: String,
    @Assisted("title") private val title: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(WebSiteUiState())
    val uiState: StateFlow<WebSiteUiState> = _uiState.asStateFlow()

    fun load() {
        _uiState.update { currentState ->
            currentState.copy(
                url = url,
                title = title
            )
        }
    }

    @AssistedFactory
    interface MyViewModelFactory {
        fun create(
            @Assisted("url") url: String,
            @Assisted("title") title: String
        ): WebSiteViewModel
    }
}