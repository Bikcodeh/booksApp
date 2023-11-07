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
        onNameChange = { signUpViewModel.sendEvent { SignUpEvent.OnNameChange(it) } },
        onEmailChange = { signUpViewModel.sendEvent { SignUpEvent.OnEmailChange(it) } },
        onPasswordChange = { signUpViewModel.sendEvent { SignUpEvent.OnPasswordChange(it) } },
        onConfirmPasswordChange = {
            signUpViewModel.sendEvent {
                SignUpEvent.OnConfirmPasswordChange(
                    it
                )
            }
        },
        onSignUp = { signUpViewModel.sendEvent { SignUpEvent.DoSignUp } }
    )
}