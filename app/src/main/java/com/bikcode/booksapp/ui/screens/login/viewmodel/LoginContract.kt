package com.bikcode.booksapp.ui.screens.login.viewmodel

import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.ui.utils.ViewEvent
import com.bikcode.booksapp.ui.utils.ViewSideEffect
import com.bikcode.booksapp.ui.utils.ViewState

sealed class LoginEffect : ViewSideEffect {
    data class Loading(val show: Boolean = false) : LoginEffect()
    data class Navigate(val route: String): LoginEffect()
}

sealed class LoginEvent : ViewEvent {
    data class OnEmailChange(val text: String) : LoginEvent()
    data class OnPasswordChange(val text: String) : LoginEvent()
    object DoLogin : LoginEvent()
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: UiText? = null,
    val passwordError: UiText? = null
) : ViewState