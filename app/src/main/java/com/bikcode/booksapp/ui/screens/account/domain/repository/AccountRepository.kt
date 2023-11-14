package com.bikcode.booksapp.ui.screens.account.domain.repository

import android.net.Uri

interface AccountRepository {
    suspend fun updatePassword(
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun updateProfilePicture(
        photoUri: Uri,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun getProfilePicture(
        onSuccess: (Uri) -> Unit,
        onError: (Throwable) -> Unit
    )
}