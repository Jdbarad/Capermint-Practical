package com.jdbarad.jaypalsinhbarad.ui.home.part1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdbarad.jaypalsinhbarad.data.model.UiState
import com.jdbarad.jaypalsinhbarad.data.remote.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class Part1ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow(Part1State())
    val state = _state.onStart {
        getUsers()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Part1State())

    private suspend fun getUsers() {
        repository.getUsers().isSuccess {
            _state.value = _state.value.copy(users = UiState.success(it.data))
        }.isError {
            _state.value = _state.value.copy(users = UiState.error(it.message))
        }
    }

}