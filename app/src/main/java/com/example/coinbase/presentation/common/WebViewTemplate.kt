package com.example.coinbase.presentation.common

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewTemplate(
    url: String,
    modifier: Modifier = Modifier,
    client: WebViewClient = WebViewClient()
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.setSettingsWebView()
                webViewClient = client
            }
        },
        update = {
            if (url.isNotEmpty()) it.loadUrl(url)
        }
    )
}

@SuppressLint("SetJavaScriptEnabled")
fun WebSettings.setSettingsWebView() {
    with(this) {
        javaScriptEnabled = true
        loadWithOverviewMode = true
        useWideViewPort = true
        setSupportZoom(true)
    }
}