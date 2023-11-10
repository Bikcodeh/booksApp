package com.bikcode.booksapp.domain.repository

interface AuthRepository {
    suspend fun doSignUp(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}