package com.example.coinbase.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.example.coinbase.utils.AppConstants

@Composable
fun HomeRoute(
    onClickCardItem: (CoinModel) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onClickCardItem = onClickCardItem
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit,
    onClickCardItem: (CoinModel) -> Unit
) {
    val searchText = when (uiState) {
        is HomeUiState.ListCoins -> uiState.searchText
        else -> AppConstants.EMPTY_STRING
    }

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

                is HomeUiState.ListCoins -> {
                    ListCoins(
                        listCoins = uiState.list,
                        onClickItem = onClickCardItem,
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
        uiState = HomeUiState.ListCoins(listOf()),
        onEvent = {},
        onClickCardItem = {}
    )
}