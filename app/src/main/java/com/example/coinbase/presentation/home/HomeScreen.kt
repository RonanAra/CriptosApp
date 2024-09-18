package com.example.coinbase.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onClickCardItem: (CoinModel) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()

    if (uiState.loading) LoadingTemplate()

    if (uiState.showError) {
        ErrorDialog(
            message = uiState.errorMessage,
            onDismiss = viewModel::dismissErrorDialog,
            onConfirm = {
                viewModel.run {
                    getCoins()
                    dismissErrorDialog()
                }
            }
        )
    }

    isConnected.value?.let { connected ->
        if (!connected) viewModel.showErrorUnknownHost()
    }

    Column(Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.assets_title),
            modifier = Modifier.padding(16.dp)
        )
        SearchTextInput(
            onValueChange = { text ->
                viewModel.filterList(text)
            }
        )
        ListCoins(
            listCoins = uiState.list,
            onClickItem = onClickCardItem
        )
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        onClickCardItem = {}
    )
}