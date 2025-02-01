package com.dyno.androidtodostarter.ui.webview

sealed class WebViewIntent {
    data class FetchData(val request: String) : WebViewIntent()
}