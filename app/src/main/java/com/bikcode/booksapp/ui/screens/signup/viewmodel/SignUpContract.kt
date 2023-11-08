package com.bikcode.booksapp.ui.screens.signup.viewmodel

import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.ui.utils.ViewEvent
import com.bikcode.booksapp.ui.utils.ViewState

sealed class SignUpEvent: ViewEvent {
    object DoSignUp: SignUpEvent()
    data class OnNameChange(val text: String): SignUpEvent()
    data class OnEmailChange(val text: String): SignUpEvent()
    data class OnPasswordChange(val text: String): SignUpEvent()
    data class OnConfirmPasswordChange(val text: String): SignUpEvent()
}

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val showLoading: Boolean = false,
    val goHome: Boolean = false
): ViewState