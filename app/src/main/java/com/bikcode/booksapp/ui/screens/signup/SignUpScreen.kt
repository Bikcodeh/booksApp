package com.bikcode.booksapp.ui.screens.signup

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpEvent
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    navigate: (String) -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    SignUpContent(
        onBack = onBack,
        navigate = navigate,
        signUpUiState = signUpViewModel.viewState,
        onNameChange = { signUpViewModel.handleEvents(SignUpEvent.OnNameChange(it)) },
        onEmailChange = { signUpViewModel.handleEvents(SignUpEvent.OnEmailChange(it)) },
        onPasswordChange = { signUpViewModel.handleEvents(SignUpEvent.OnPasswordChange(it)) },
        onConfirmPasswordChange = {
            signUpViewModel.handleEvents(
                SignUpEvent.OnConfirmPasswordChange(it)
            )
        },
        onSignUp = { signUpViewModel.handleEvents(SignUpEvent.DoSignUp) }
    )
}