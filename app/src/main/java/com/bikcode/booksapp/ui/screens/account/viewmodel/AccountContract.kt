package com.bikcode.booksapp.ui.screens.account.viewmodel

import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.model.User
import com.bikcode.booksapp.ui.utils.ViewEvent

sealed class AccountEvent: ViewEvent {
    data class OnNameChange(val text: String): AccountEvent()
    object OnSave: AccountEvent()
    object OnChangePasswordClick: AccountEvent()
    object OnChangePasswordSave: AccountEvent()
}

data class AccountUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val name: String = "",
    val image: String = "",
    val error: UiText? = null
)