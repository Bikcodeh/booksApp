package com.bikcode.booksapp.ui.screens.signup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.auth.DoSignUpUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmailUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidateEmptyFieldUseCase
import com.bikcode.booksapp.domain.usecase.validation.ValidatePasswordUseCase
import com.bikcode.booksapp.ui.utils.MVIViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateEmptyFieldUseCase: ValidateEmptyFieldUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val doSignUpUseCase: DoSignUpUseCase,
    dispatcher: DispatcherProvider
) : MVIViewModel<SignUpEffect, SignUpEvent>(dispatcher) {

    var viewState by mutableStateOf(SignUpUiState())

    override fun handleEvents(event: SignUpEvent) {
        when (event) {
            SignUpEvent.DoSignUp -> {
                signUp()
            }

            is SignUpEvent.OnConfirmPasswordChange -> {
                viewState = viewState.copy(confirmPassword = event.text)
                validateConfirmPassword()
            }

            is SignUpEvent.OnEmailChange -> {
                viewState = viewState.copy(email = event.text)
                validateEmail()
            }

            is SignUpEvent.OnNameChange -> {
                viewState = viewState.copy(name = event.text)
                validateName()
            }

            is SignUpEvent.OnPasswordChange -> {
                viewState = viewState.copy(password = event.text)
                validatePassword()
            }
        }
    }

    private fun validateName(): Boolean {
        val nameResult = validateEmptyFieldUseCase.execute(viewState.name)
        viewState = viewState.copy(nameError = nameResult.errorMessage)
        return nameResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(viewState.email)
        viewState = viewState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(viewState.password)
        viewState = viewState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateConfirmPassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(viewState.confirmPassword)
        viewState = viewState.copy(confirmPasswordError = passwordResult.errorMessage)
        if (passwordResult.successful) {
            viewState = if (viewState.password == viewState.confirmPassword) {
                viewState.copy(confirmPasswordError = null)
            } else {
                viewState.copy(confirmPasswordError = UiText.StringResource(R.string.password_not_match))
            }
        }
        return passwordResult.successful
    }

    private fun signUp() {
        if (validateEmail() && validatePassword() && validateName()) {
            setEffect { SignUpEffect.Loading(show = true) }
            val data = hashMapOf<String, Any>().apply {
                put("name", viewState.name)
                put("email", viewState.email)
                put("password", viewState.password)
                put("roleId", "Lwi3O8OEYk70sxSM1r0r")
            }
            doSignUpUseCase.invoke(
                data = data,
                onSuccess = {
                    setEffect { SignUpEffect.Loading(show = false) }
                    setEffect { SignUpEffect.GoHome }
                },
                onError = {
                    setEffect { SignUpEffect.Loading(show = false) }
                }
            )
        }
    }
}