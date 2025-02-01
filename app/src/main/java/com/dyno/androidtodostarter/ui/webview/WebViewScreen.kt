package com.dyno.androidtodostarter.ui.webview

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.dyno.androidtodostarter.ui.webview.WebViewState.*
import com.dyno.androidtodostarter.ui.webview.bridge.WebViewBridge
import com.dyno.androidtodostarter.ui.webview.bridge.WebViewCaller


@Composable
fun WebViewScreen(viewModel: WebViewViewModel) {
    val url = "file:///android_asset/index.html" // URL

    Column(modifier = Modifier.fillMaxSize()) {
        CustomWebView(url = url, viewModel = viewModel)
    }
}

@SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
@Composable
fun CustomWebView(
    url: String,
    viewModel: WebViewViewModel
) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    val webViewBridge = remember {
        WebViewBridge(object : WebViewCaller {
            override fun loadUrl(url: String) {
                webView.loadUrl(url)
            }

        }) { intent ->
            viewModel.processIntent(intent)
        }
    }

    LaunchedEffect(viewModel.webViewState) {
        viewModel.webViewState.collect { state ->
            when (state) {
                is Content -> webViewBridge.sendDataToWebView(state.data)
                is Error -> Log.e("WebView", state.message)
                Loading -> Log.d("WebView", "Loading...")
                else -> {}
            }
        }
    }


    AndroidView(
        factory = {
            webView.apply {
                settings.javaScriptEnabled = true
                addJavascriptInterface(webViewBridge, "AndroidBridge")
                loadUrl(url)
            }
        },
        update = { webViewInstance ->
            webViewInstance.loadUrl(url)
        }
    )
}

