package com.example.soundsense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel(private val stt: SpeechToText) : ViewModel() {
    var state by mutableStateOf(AppState())
        private set

    init {
        viewModelScope.launch {
            with(stt) {
                text.collect { result ->
                    send(AppAction.Update(result))
                }
            }
        }
    }


    fun send(action: AppAction) {
        when (action) {
            AppAction.StartRecord -> {
                state = state.copy(display = "")
                stt.start()
            }

            AppAction.EndRecord -> {
                stt.stop()
            }
            is AppAction.Update -> {
                state = state.copy(
                    display = action.text
                )
            }
        }
    }
}

data class AppState(
    val display: String = ""
)

sealed class AppAction {
    object StartRecord : AppAction()
    object EndRecord : AppAction()
    data class Update(val text: String): AppAction()
}