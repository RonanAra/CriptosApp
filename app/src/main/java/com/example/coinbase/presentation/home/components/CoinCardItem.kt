package com.example.coinbase.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.coinbase.R
import com.example.coinbase.domain.entity.CoinModel
import android.graphics.Color as ColorGraphic

@Composable
fun CoinCardItem(
    item: CoinModel,
    onClickItem: (CoinModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(8.dp)
            .clickable { onClickItem(item) },
        colors = CardDefaults.cardColors(
            containerColor = Color(
                ColorGraphic.parseColor(item.color)
            )
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .error(R.drawable.no_image_available)
                    .crossfade(true)
                    .crossfade(1000)
                    .build(),
                contentDescription = item.name,
                modifier = Modifier
                    .size(70.dp)
                    .padding(12.dp),
                loading = {
                    CircularProgressIndicator(
                        color = Color.Blue
                    )
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp,),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.symbol,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val item = CoinModel(
        color = "#F7931A",
        id = "5b71fc48-3dd3-540c-809b-f8c94d0e68b5",
        imageUrl = "https://dynamic-assets.coinbase.com/" +
                "e785e0181f1a23a30d9476038d9be91e9f6c63959b538eabbc51a1abc889" +
                "8940383291eede695c3b8dfaa1829a9b57f5a2d0a16b0523580346c6b8fab6" +
                "7af14b/asset_icons/b57ac673f06a4b0338a596817eb0a50ce16e2059f327dc117744449a47915cb2.png",
        name = "Bitcoin",
        symbol = "BTC",
        website = "https://bitcoin.org"
    )
    CoinCardItem(
        item = item,
        onClickItem = {}
    )
}