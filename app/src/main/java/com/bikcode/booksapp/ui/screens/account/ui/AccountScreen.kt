package com.bikcode.booksapp.ui.screens.account.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.screens.account.ui.viewmodel.AccountViewModel

@Composable
fun AccountScreen(
    navigate: (String) -> Unit,
    paddingValues: PaddingValues,
    onLogOut: () -> Unit,
    showSnackBar: (String) -> Unit,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val state by rememberUpdatedState(newValue = accountViewModel.viewState)
    val passwordClick = remember { navigate }
    AccountContent(
        paddingValues = paddingValues,
        onLogOut = onLogOut,
        uiState = state,
        onChangePasswordClick = { passwordClick.invoke(Screens.ChangePassword.route) },
        onPersonalInfoClick = { },
        showSnackBar = showSnackBar
    )
}