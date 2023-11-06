package com.bikcode.booksapp.ui.screens.signup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : MVIViewModel<SignUpEffect, SignUpEvent>(dispatcher) {

    var viewState by mutableStateOf(SignUpUiState())

    override fun handleEvents(event: SignUpEvent) {
        when (event) {
            SignUpEvent.DoSignUp -> {
                signUp()
            }
            is SignUpEvent.OnConfirmPasswordChange -> viewState = viewState.copy(confirmPassword = event.text)
            is SignUpEvent.OnEmailChange -> viewState = viewState.copy(email = event.text)
            is SignUpEvent.OnNameChange -> viewState = viewState.copy(name = event.text)
            is SignUpEvent.OnPasswordChange -> viewState = viewState.copy(password = event.text)
        }
    }

    private fun signUp() {

    }
}