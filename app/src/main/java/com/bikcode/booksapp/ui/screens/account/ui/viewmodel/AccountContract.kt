package com.bikcode.booksapp.ui.screens.account.ui.viewmodel

import android.net.Uri
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.model.User
import com.bikcode.booksapp.ui.utils.ViewEvent

sealed class AccountEvent : ViewEvent {
    data class OnNameChange(val text: String) : AccountEvent()
    object OnSave : AccountEvent()
    object OnChangePasswordClick : AccountEvent()
    object OnChangePasswordSave : AccountEvent()
    data class OnPasswordChange(val text: String): AccountEvent()
    data class OnConfirmPasswordChange(val text: String): AccountEvent()
    data class UpdateProfilePicture(val photoUri: Uri): AccountEvent()
}

data class AccountUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val name: String = "",
    val image: String = "",
    val error: UiText? = null,
    val picture: Uri? = null
)

data class ChangePasswordUiState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val alertText: UiText? = null
)