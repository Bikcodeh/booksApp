package com.bikcode.booksapp.ui.screens.account.data.repository

import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.screens.account.domain.repository.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : AccountRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override suspend fun updatePassword(
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            auth.currentUser?.let {
                it.updatePassword(password).addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener { error ->
                    onError(error)
                }
            }
        }
    }
}