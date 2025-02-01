package com.dyno.androidtodostarter.ui.webview.bridge

import android.webkit.JavascriptInterface
import com.dyno.androidtodostarter.ui.webview.WebViewIntent
import com.google.gson.Gson

interface WebViewCaller {
    fun loadUrl(url: String)
}

class WebViewBridge(
    private val webView: WebViewCaller,
    private val callback: (WebViewIntent) -> Unit
) {
    private val gson = Gson()

    @JavascriptInterface
    fun fetchDataFromAndroid(body: String) {
        callback(WebViewIntent.FetchData)
    }

    fun sendDataToWebView(data: Any) {
        val jsonData = gson.toJson(WebViewResponse(data)) // Any 타입을 JSON 문자열로 변환
        val jsCode = "javascript:response($jsonData)"
        webView.loadUrl(jsCode)
    }
}