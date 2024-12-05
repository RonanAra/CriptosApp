package com.example.coinbase.presentation.home

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coinbase.R
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.presentation.common.ErrorDialog
import com.example.coinbase.presentation.common.LoadingTemplate
import com.example.coinbase.presentation.home.components.ListCoins
import com.example.coinbase.presentation.home.components.SearchTextInput
import com.example.coinbase.presentation.home.viewmodel.HomeUiState
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel
import com.example.coinbase.utils.extensions.clearFocusOnKeyboardDismiss

@Composable
fun HomeRoute(
    onClickCardItem: (CoinModel) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.UiToWebSite -> onClickCardItem(event.item)
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        searchText = viewModel.searchText,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    searchText: String,
    onEvent: (HomeEvent) -> Unit
) {
    val activity = LocalActivity.current ?: error("Activity is required")
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Text(
                text = stringResource(R.string.assets_title),
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchTextInput(
                modifier = Modifier
                    .clearFocusOnKeyboardDismiss(
                        activity = activity,
                        focusManager = focusManager
                    ),
                searchText = searchText,
                onValueChange = { onEvent(HomeEvent.SearchCoinByName(it)) }
            )
            when (uiState) {
                is HomeUiState.Loading -> LoadingTemplate()
                is HomeUiState.Error -> {
                    ErrorDialog(
                        message = uiState.message,
                        onConfirm = { onEvent(HomeEvent.LoadCoins) }
                    )
                }

                is HomeUiState.FetchCoins -> {
                    ListCoins(
                        listCoins = uiState.list,
                        onClickItem = { item ->
                            onEvent(HomeEvent.OnClickCardItem(item))
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        uiState = HomeUiState.FetchCoins(listOf()),
        searchText = "",
        onEvent = {}
    )
}