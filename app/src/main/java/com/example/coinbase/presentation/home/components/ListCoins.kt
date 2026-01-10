package com.example.coinbase.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.presentation.common.PullToTopButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCoins(
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
    listCoins: List<CoinModel>?,
    onClickItem: (CoinModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

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
                .padding(top = 4.dp),
            state = listState
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
        if (showButton) {
            PullToTopButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onCLick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            )
        }
    }
}