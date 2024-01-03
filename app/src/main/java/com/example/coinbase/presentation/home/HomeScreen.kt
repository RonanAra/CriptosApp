package com.example.coinbase.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinbase.R
import com.example.coinbase.presentation.common.GenericLoadingTemplate
import com.example.coinbase.presentation.home.components.ListCoins
import com.example.coinbase.presentation.home.components.SearchTextInput

@Composable
fun HomeScreen(
    onClickCardItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.handleIntent(HomeIntent.LoadCoins)
    }

    if (uiState.loading) {
        GenericLoadingTemplate()
    } else {
        Column(modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.assets_title),
                modifier = Modifier.padding(16.dp)
            )
            SearchTextInput(
                onValueChange = { text ->
                    viewModel.handleIntent(HomeIntent.FilterList(text))
                }
            )
            ListCoins(
                listCoins = uiState.list,
                onClickItem = { onClickCardItem(it.website) },
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        onClickCardItem = {}
    )
}