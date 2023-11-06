package com.bikcode.booksapp.domain.repository

interface AuthRepository {
    fun doSignUp(
        data: HashMap<String, Any>,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}