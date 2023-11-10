package com.bikcode.booksapp.domain.usecase.auth

import com.bikcode.booksapp.domain.repository.AuthRepository
import javax.inject.Inject

class DoSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        authRepository.doSignUp(name, email, password, onSuccess, onError)
    }
}