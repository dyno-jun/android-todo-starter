package com.dyno.androidtodostarter.ui.webview

sealed class WebViewIntent {
    data object FetchData : WebViewIntent()
}