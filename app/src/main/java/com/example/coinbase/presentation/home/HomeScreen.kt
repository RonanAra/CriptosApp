package com.example.coinbase.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.coinbase.R
import com.example.coinbase.presentation.common.GenericLoadingTemplate
import com.example.coinbase.presentation.home.components.ListCoins
import com.example.coinbase.presentation.home.components.SearchTextInput
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onClickCardItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) { viewModel.getCoins() }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )

    if (uiState.loading) GenericLoadingTemplate()

    if (uiState.list.isNotEmpty()) {
        Column(modifier.fillMaxSize()) {
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