package com.example.coinbase.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.coinbase.presentation.common.LoadingTemplate
import com.example.coinbase.presentation.detail.viewmodel.WebSiteViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CoinDetailWebView(
    url: String,
    modifier: Modifier = Modifier,
    viewModel: WebSiteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val webViewState = rememberWebViewState(uiState.url)

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.load(url)
    }

    if (webViewState.isLoading) LoadingTemplate()

    if (uiState.url.isNotEmpty()) {
        WebView(
            modifier = modifier,
            state = webViewState,
            captureBackPresses = true,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CoinDetailWebView(url = "")
}