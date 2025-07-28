package com.example.coinbase.presentation.home

import android.text.Spanned
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coinbase.R
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.presentation.common.ErrorDialog
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
        viewModel.events.collect { event ->
            when (event) {
                is HomeUiEvent.NavigateToCoinWebSite -> onClickCardItem(event.item)
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            Text(
                text = stringResource(R.string.assets_title).AnnotatedString(),
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
                modifier = Modifier.clearFocusOnKeyboardDismiss(),
                searchText = uiState.searchText,
                onValueChange = { onEvent(HomeEvent.SearchCoinByName(it)) }
            )

            uiState.errorMessage?.let { message ->
                ErrorDialog(
                    message = message,
                    onConfirm = {
                        onEvent(HomeEvent.HideErrorDialog)
                        onEvent(HomeEvent.LoadCoins)
                    }
                )
            }

            ListCoins(
                listCoins = uiState.coins,
                isRefreshing = uiState.isRefreshing,
                onRefresh = { onEvent(HomeEvent.LoadCoins) },
                onClickItem = { item ->
                    onEvent(HomeEvent.OnClickCardItem(item))
                }
            )
        }
    }
}

fun String.AnnotatedString(): AnnotatedString {
    val spanned: Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return AnnotatedString(spanned.toString())
}

@Preview
@Composable
private fun Preview() {
    HomeScreen(
        uiState = HomeUiState(),
        onEvent = {}
    )
}