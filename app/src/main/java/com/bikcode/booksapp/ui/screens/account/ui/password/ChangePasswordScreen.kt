package com.bikcode.booksapp.ui.screens.account.ui.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.account.ui.viewmodel.AccountEvent
import com.bikcode.booksapp.ui.screens.account.ui.viewmodel.AccountViewModel

@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val state by rememberUpdatedState(newValue = accountViewModel.changePasswordViewState)
    ChangePasswordContent(
        onBack = onBack,
        onChangePassword = {
            accountViewModel.handleEvents(AccountEvent.OnPasswordChange(it))
        },
        onChangeConfirmPassword = {
            accountViewModel.handleEvents(AccountEvent.OnConfirmPasswordChange(it))
        },
        uiState = state,
        currentPassword = accountViewModel.viewState.user?.password
    )
}