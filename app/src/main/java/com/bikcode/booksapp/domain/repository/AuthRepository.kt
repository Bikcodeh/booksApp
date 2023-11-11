package com.bikcode.booksapp.domain.repository

import com.bikcode.booksapp.domain.model.User

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

    suspend fun getAuthUser(
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    )
}