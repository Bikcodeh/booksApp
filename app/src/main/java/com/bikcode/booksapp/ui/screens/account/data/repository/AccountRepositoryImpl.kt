package com.bikcode.booksapp.ui.screens.account.data.repository

import android.net.Uri
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.screens.account.domain.repository.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
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

    override suspend fun updateProfilePicture(
        photoUri: Uri,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        auth.currentUser?.let { user ->
            val reference = FirebaseStorage.getInstance().reference
            val imageRef = reference.child("images")
            val image = imageRef.child(user.uid)
            image.putFile(photoUri).addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onError(it)
            }
        }
    }
}