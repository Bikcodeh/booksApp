package com.bikcode.booksapp.ui.screens.account.domain.repository

interface AccountRepository {
    suspend fun updatePassword(
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}