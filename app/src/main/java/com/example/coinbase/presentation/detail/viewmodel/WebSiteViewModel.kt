package com.example.coinbase.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WebSiteViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(WebSiteUiState())
    val uiState: StateFlow<WebSiteUiState> = _uiState.asStateFlow()

    fun load(url: String) {
        _uiState.update { currentState ->
            currentState.copy(url = url)
        }
    }
}