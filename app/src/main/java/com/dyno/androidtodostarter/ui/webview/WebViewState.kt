package com.dyno.androidtodostarter.ui.webview

import com.dyno.androidtodostarter.data.Todo

sealed class WebViewState {
    data object Loading : WebViewState() // 로딩 중
    data class Content(val data: List<Todo>) : WebViewState() // 데이터 로드 완료
    data class Error(val message: String) : WebViewState() // 오류 발생
}