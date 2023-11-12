package com.bikcode.booksapp.ui.screens.account.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val authRepository: AuthRepository
) : MVIViewModel<AccountEvent>() {

    var viewState by mutableStateOf(AccountUiState())
    var changePasswordViewState by mutableStateOf(ChangePasswordUiState())
    override fun handleEvents(event: AccountEvent) {
        when (event) {
            AccountEvent.OnChangePasswordClick -> {}
            AccountEvent.OnChangePasswordSave -> {}
            is AccountEvent.OnNameChange -> {
                viewState = viewState.copy(name = event.text)
            }

            AccountEvent.OnSave -> {}
            is AccountEvent.OnConfirmPasswordChange -> changePasswordViewState =
                changePasswordViewState.copy(confirmPassword = event.text)

            is AccountEvent.OnPasswordChange -> changePasswordViewState =
                changePasswordViewState.copy(password = event.text)
        }
    }

    init {
        viewState = viewState.copy(isLoading = true, error = null)
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.getAuthUser(
                onSuccess = {
                    viewState =
                        viewState.copy(user = it, error = null, name = it.name, isLoading = false)
                },
                onError = {
                    viewState =
                        viewState.copy(
                            isLoading = false,
                            error = UiText.StringResource(R.string.error_fetching_user_data)
                        )
                }
            )
        }
    }
}