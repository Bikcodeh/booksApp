package com.bikcode.booksapp.domain.usecase.auth

import com.bikcode.booksapp.domain.repository.AuthRepository
import javax.inject.Inject

class DoSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        data: HashMap<String, Any>,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        authRepository.doSignUp(data, onSuccess, onError)
    }
}