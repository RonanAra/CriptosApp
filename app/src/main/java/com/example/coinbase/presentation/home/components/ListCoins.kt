package com.example.coinbase.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coinbase.domain.entity.CoinModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCoins(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    listCoins: List<CoinModel>?,
    onClickItem: (CoinModel) -> Unit,
    modifier: Modifier = Modifier
) {
    if (listCoins?.isEmpty() == true) EmptyPlaceHolder()
    else PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
        ) {
            listCoins?.let {
                items(it) { item ->
                    CoinCardItem(
                        item = item,
                        onClickItem = { onClickItem(item) }
                    )
                }
            }
        }
    }
}