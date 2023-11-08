package com.bikcode.booksapp.ui.screens.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.R
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.auth.DoLoginUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmailUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmptyFieldUseCase
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateEmptyFieldUseCase: ValidateEmptyFieldUseCase,
    private val doLoginUseCase: DoLoginUseCase,
    dispatcher: DispatcherProvider
) : MVIViewModel<LoginEvent>(dispatcher) {

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
            viewState = viewState.copy(showLoading = true)
            doLoginUseCase(
                viewState.email,
                viewState.password,
                onSuccess = {
                    viewState = viewState.copy(goToHome = true, showLoading = false)

                },
                onError = { viewState = viewState.copy(showLoading = false) }
            )
        } else {
            validateEmail()
            validatePassword()
        }
    }
}