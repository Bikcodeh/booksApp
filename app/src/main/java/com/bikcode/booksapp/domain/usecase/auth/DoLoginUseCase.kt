package com.bikcode.booksapp.domain.usecase.auth

import com.bikcode.booksapp.domain.repository.AuthRepository

class DoLoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        authRepository.doLogin(email, password, onSuccess, onError)
    }
}