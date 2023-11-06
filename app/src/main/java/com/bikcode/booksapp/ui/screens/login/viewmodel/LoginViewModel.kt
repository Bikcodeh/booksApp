package com.bikcode.booksapp.ui.screens.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.auth.DoLoginUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmailUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmptyFieldUseCase
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpUiState
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateEmptyFieldUseCase: ValidateEmptyFieldUseCase,
    private val doLoginUseCase: DoLoginUseCase,
    dispatcher: DispatcherProvider
) : MVIViewModel<LoginEffect, LoginEvent>(dispatcher) {

    var viewState by mutableStateOf(LoginUiState())
    override fun handleEvents(event: LoginEvent) {
        when (event) {
            LoginEvent.DoLogin -> {
                doLogin()
            }

            is LoginEvent.OnEmailChange -> {
                viewState = viewState.copy(email = event.text)
                validateEmail()
            }

            is LoginEvent.OnPasswordChange -> {
                viewState = viewState.copy(password = event.text)
                validatePassword()
            }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordResult =
            validateEmptyFieldUseCase.execute(viewState.password, R.string.password_required)
        viewState =
            viewState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(viewState.email)
        viewState = viewState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun doLogin() {
        if (validateEmail() && validatePassword()) {
            setEffect { LoginEffect.Loading(true) }
            doLoginUseCase(
                viewState.email,
                viewState.password,
                onSuccess = {
                    setEffect { LoginEffect.Loading(false) }
                    setEffect { LoginEffect.Navigate(Screens.Home.route) }
                },
                onError = {
                    setEffect { LoginEffect.Loading(false) }
                }
            )
        } else {
            validateEmail()
            validatePassword()
        }
    }
}