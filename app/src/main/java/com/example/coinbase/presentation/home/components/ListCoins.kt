package com.example.coinbase.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coinbase.domain.entity.CoinModel

@Composable
fun ListCoins(
    listCoins: List<CoinModel>,
    onClickItem: (CoinModel) -> Unit,
    modifier: Modifier = Modifier
) {
    if (listCoins.isEmpty()) EmptyPlaceHolder()
    else LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(listCoins) { item ->
            CoinCardItem(
                item = item,
                onClickItem = { onClickItem(item) }
            )
        }
    }
}