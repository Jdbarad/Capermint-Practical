package com.jdbarad.jaypalsinhbarad.ui.home.part2

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class Part2ViewModel @Inject constructor() :ViewModel() {

    private val _state = MutableStateFlow(Part2State())
    val state = _state.asStateFlow()

    fun onGenerateClick() {
        val count = _state.value.inputText.toIntOrNull() ?: 0
        _state.value = _state.value.copy(boxCount = count, inputText = "")
    }

    fun onTextChange(count: String) {
        _state.value = _state.value.copy(inputText = count)
    }

}

data class Part2State(
    val inputText:String = "",
    val boxCount:Int = 0
)