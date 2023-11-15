package com.bikcode.booksapp.ui.screens.account.data.repository

import android.net.Uri
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.ui.screens.account.domain.repository.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : AccountRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override suspend fun updatePassword(
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            auth.currentUser?.let { user ->
                user.updatePassword(password).addOnSuccessListener {
                   db.collection(USER_REFERENCE).document(user.uid)
                       .update(PASSWORD_KEY, password)
                       .addOnSuccessListener {
                           onSuccess()
                       }.addOnFailureListener(onError)
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
        withContext(dispatcherProvider.io) {
            auth.currentUser?.let { user ->
                val imageRef = storage.reference.child("images")
                val image = imageRef.child(user.uid)
                image.putFile(photoUri).addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener {
                    onError(it)
                }
            }
        }
    }

    override suspend fun getProfilePicture(onSuccess: (Uri) -> Unit, onError: (Throwable) -> Unit) {
        withContext(dispatcherProvider.io) {
            auth.currentUser?.let { user ->
                storage.reference.child("images").child(user.uid)
                    .downloadUrl
                    .addOnSuccessListener {
                        onSuccess(it)
                    }.addOnFailureListener { onError(it)  }
            }
        }
    }

    companion object {
        private const val USER_REFERENCE = "users"
        private const val UID_KEY = "uid"
        private const val PASSWORD_KEY = "password"
    }
}