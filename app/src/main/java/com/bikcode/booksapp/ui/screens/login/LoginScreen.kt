package com.bikcode.booksapp.ui.screens.login

import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginEvent
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navigate: (String) -> Unit, loginViewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
    LoginContent(
        navigate = navigate,
        loginUiState = loginViewModel.viewState,
        onLogin = { loginViewModel.handleEvents(LoginEvent.DoLogin) },
        onEmailChange = { loginViewModel.handleEvents(LoginEvent.OnEmailChange(it)) },
        onPasswordChange = { loginViewModel.handleEvents(LoginEvent.OnPasswordChange(it)) }
    )
}