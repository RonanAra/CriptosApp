package com.example.coinbase.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.coinbase.presentation.common.AppTopBar
import com.example.coinbase.presentation.common.LoadingTemplate
import com.example.coinbase.presentation.detail.viewmodel.WebSiteViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CoinDetailWebView(
    modifier: Modifier = Modifier,
    navigationUpCallback: () -> Unit = {},
    viewModel: WebSiteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val webViewState = rememberWebViewState(uiState.url)

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.load()
    }

    if (webViewState.isLoading) LoadingTemplate()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = uiState.title,
                navigationUpCallback = navigationUpCallback
            )
        }
    ) { paddingValues ->
        if (uiState.url.isNotEmpty()) {
            WebView(
                modifier = Modifier.padding(paddingValues),
                state = webViewState,
                captureBackPresses = true,
                onCreated = { webView ->
                    webView.settings.javaScriptEnabled = true
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CoinDetailWebView()
}