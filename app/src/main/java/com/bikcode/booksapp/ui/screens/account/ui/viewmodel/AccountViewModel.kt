package com.bikcode.booksapp.ui.screens.account.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.validation.ValidatePasswordUseCase
import com.bikcode.booksapp.ui.screens.account.domain.repository.AccountRepository
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: AuthRepository,
    private val accountRepository: AccountRepository,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
) : MVIViewModel<AccountEvent>() {

    var viewState by mutableStateOf(AccountUiState())
    var changePasswordViewState by mutableStateOf(ChangePasswordUiState())
    override fun handleEvents(event: AccountEvent) {
        when (event) {
            AccountEvent.OnChangePasswordSave -> updatePassword()
            is AccountEvent.OnNameChange -> {
                viewState = viewState.copy(name = event.text)
            }

            is AccountEvent.OnConfirmPasswordChange -> {
                changePasswordViewState = changePasswordViewState.copy(confirmPassword = event.text)
                validateConfirmPassword()
            }

            is AccountEvent.OnPasswordChange -> {
                changePasswordViewState = changePasswordViewState.copy(password = event.text)
                validatePassword()
            }

            is AccountEvent.UpdateProfilePicture -> updateProfilePicture(event.photoUri)
        }
    }

    private fun updatePassword() {
        changePasswordViewState =
            changePasswordViewState.copy(isLoading = true, error = null, alertText = null)
        viewModelScope.launch(dispatcherProvider.io) {
            accountRepository.updatePassword(changePasswordViewState.password,
                onSuccess = {
                    changePasswordViewState = changePasswordViewState.copy(
                        isLoading = false,
                        error = null,
                        password = "",
                        confirmPassword = "",
                        alertText = UiText.StringResource(R.string.password_updated)
                    )
                }, onError = {
                    changePasswordViewState = changePasswordViewState.copy(
                        isLoading = false,
                        error = UiText.StringResource(R.string.error_updating_password),
                        alertText = null
                    )
                }
            )
        }

    }

    private fun fetchProfilePicture() {
        viewModelScope.launch(dispatcherProvider.io) {
            accountRepository.getProfilePicture(
                onSuccess = {
                    viewState = viewState.copy(picture = it)
                }, onError = {
                    viewState =
                        viewState.copy(
                            error = UiText.StringResource(R.string.error_loading_image),
                            picture = null
                        )
                }
            )
        }
    }

    private fun updateProfilePicture(photoUri: Uri) {
        viewModelScope.launch(dispatcherProvider.io) {
            accountRepository.updateProfilePicture(
                photoUri = photoUri,
                onSuccess = {
                    viewState = viewState.copy(error = null)
                    fetchProfilePicture()
                },
                onError = {
                    viewState =
                        viewState.copy(error = UiText.StringResource(R.string.error_photo))
                }
            )
        }
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(changePasswordViewState.password)
        changePasswordViewState = changePasswordViewState.copy(
            passwordError = passwordResult.errorMessage,
            formValid = changePasswordViewState.password == changePasswordViewState.confirmPassword
        )
        if (passwordResult.successful && changePasswordViewState.confirmPassword.isNotEmpty()) {
            changePasswordViewState = changePasswordViewState.copy(
                passwordError = if (
                    changePasswordViewState.password == changePasswordViewState.confirmPassword
                ) null else UiText.StringResource(R.string.password_not_match)
            )
        }
        return passwordResult.successful
    }

    private fun validateConfirmPassword(): Boolean {
        val passwordResult =
            validatePasswordUseCase.execute(changePasswordViewState.confirmPassword)
        changePasswordViewState = changePasswordViewState.copy(
            confirmPasswordError = passwordResult.errorMessage,
            formValid = changePasswordViewState.password == changePasswordViewState.confirmPassword
        )
        if (passwordResult.successful && changePasswordViewState.confirmPassword.isNotEmpty()) {
            changePasswordViewState = changePasswordViewState.copy(
                confirmPasswordError = if (
                    changePasswordViewState.password == changePasswordViewState.confirmPassword
                ) null else UiText.StringResource(R.string.password_not_match)
            )
        }
        return passwordResult.successful
    }


    init {
        fetchProfilePicture()
        viewState = viewState.copy(isLoading = true, error = null, picture = null)
        viewModelScope.launch(dispatcherProvider.io) {
            authRepository.getAuthUser(
                onSuccess = {
                    viewState =
                        viewState.copy(
                            user = it,
                            error = null,
                            name = it.name,
                            isLoading = false
                        )
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