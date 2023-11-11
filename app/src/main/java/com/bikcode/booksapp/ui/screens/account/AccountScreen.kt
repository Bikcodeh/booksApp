package com.bikcode.booksapp.ui.screens.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.screens.account.viewmodel.AccountViewModel

@Composable
fun AccountScreen(
    navigate: (String) -> Unit,
    paddingValues: PaddingValues,
    onLogOut: () -> Unit,
    showSnackBar: (String) -> Unit,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    AccountContent(
        paddingValues = paddingValues,
        onLogOut = onLogOut,
        uiState = accountViewModel.viewState,
        onChangePasswordClick = { navigate(Screens.ChangePassword.route) },
        onPersonalInfoClick = { },
        showSnackBar = showSnackBar
    )
}