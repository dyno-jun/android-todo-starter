package com.dyno.androidtodostarter.ui.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyno.androidtodostarter.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    private val _webViewState = MutableStateFlow<WebViewState>(WebViewState.Loading)
    val webViewState: StateFlow<WebViewState> = _webViewState.asStateFlow()

    fun processIntent(intent: WebViewIntent) {
        when (intent) {
            is WebViewIntent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val result = todoRepository.findAll() // API or DB에서 데이터 가져오기
                _webViewState.value = WebViewState.Content(result.first())
            } catch (e: Exception) {
                _webViewState.value = WebViewState.Error("Failed to load data")
            }
        }
    }

}
