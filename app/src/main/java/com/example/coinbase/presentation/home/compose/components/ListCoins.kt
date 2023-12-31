package com.example.coinbase.presentation.home.compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.presentation.home.HomeViewModel

@Composable
fun ListCoins(
    onClickItem: (CoinResponse) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    val state by homeViewModel.uiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(top = 4.dp)
    ) {
        items(state.list) { item ->
            CoinCardItem(
                item = item,
                onClickItem = { onClickItem(item) }
            )
        }
    }
}