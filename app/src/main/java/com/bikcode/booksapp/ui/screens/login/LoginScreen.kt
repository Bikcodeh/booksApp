package com.bikcode.booksapp.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginEvent
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navigate: (String) -> Unit, loginViewModel: LoginViewModel = hiltViewModel()) {
    LoginContent(
        navigate = navigate,
        loginUiState = loginViewModel.viewState,
        onLogin = { loginViewModel.handleEvents(LoginEvent.DoLogin) },
        onEmailChange = { loginViewModel.handleEvents(LoginEvent.OnEmailChange(it)) },
        onPasswordChange = { loginViewModel.handleEvents(LoginEvent.OnPasswordChange(it)) }
    )
}