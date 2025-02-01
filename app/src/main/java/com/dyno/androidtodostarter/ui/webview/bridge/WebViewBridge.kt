package com.dyno.androidtodostarter.ui.webview.bridge

import android.webkit.JavascriptInterface
import com.dyno.androidtodostarter.ui.webview.WebViewIntent
import com.dyno.androidtodostarter.ui.webview.bridge.dto.request.TodoRequest
import com.dyno.androidtodostarter.ui.webview.bridge.dto.response.ErrorResponse
import com.dyno.androidtodostarter.ui.webview.bridge.dto.response.WebViewResponse
import com.google.gson.Gson

interface WebViewCaller {
    fun loadUrl(url: String)
}

class WebViewBridge(
    private val webView: WebViewCaller,
    private val callback: (WebViewIntent) -> Unit,
) {
    private val gson = Gson()

    @JavascriptInterface
    fun getTodos(body: String) {
        try {
            val request = gson.fromJson(body, TodoRequest::class.java)
            callback(WebViewIntent.FetchData(request.filter))
        } catch (e: Exception) {
            onError(e.message ?: "Error")
        }
    }

    private fun onError(message: String) {
        val jsonData = gson.toJson(ErrorResponse(message)) // Any 타입을 JSON 문자열로 변환
        val jsCode = "javascript:onError($jsonData)"
        webView.loadUrl(jsCode)
    }

    fun onSuccess(data: Any) {
        val jsonData = gson.toJson(WebViewResponse(data)) // Any 타입을 JSON 문자열로 변환
        val jsCode = "javascript:onSuccess($jsonData)"
        webView.loadUrl(jsCode)
    }
}
