package com.example.coinbase.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CoinDetailWebView(
    url: String,
    modifier: Modifier = Modifier
) {
    val webViewState = rememberWebViewState(url)
    WebView(
        modifier = modifier,
        state = webViewState,
        captureBackPresses = true,
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
        }
    )
}

@Preview
@Composable
private fun Preview() {
    CoinDetailWebView(url = "")
}