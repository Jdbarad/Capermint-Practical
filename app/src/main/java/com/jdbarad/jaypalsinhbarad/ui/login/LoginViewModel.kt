package com.jdbarad.jaypalsinhbarad.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    lateinit var onErrorMessage: (String) -> Unit
    lateinit var onNavigateToPart1: () -> Unit

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onLoginCLick() {
        if (_state.value.email.isBlank()) {
            onErrorMessage("Email is required")
            return
        }
        if (_state.value.password.isBlank()) {
            onErrorMessage("Password is required")
            return
        }
        if (_state.value.email.isMail()) {
            onErrorMessage("Email is not valid")
            return
        }
        if (_state.value.password.length < 8) {
            onErrorMessage("Password must be at least 8 characters")
            return
        }
        onNavigateToPart1()
    }

}

fun String.isMail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches().not()
}