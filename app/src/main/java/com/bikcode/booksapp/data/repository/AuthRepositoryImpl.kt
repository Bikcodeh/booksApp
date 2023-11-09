package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override suspend fun doSignUp(
        data: HashMap<String, Any>,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            FirebaseFirestore.getInstance().collection(USER_REFERENCE).add(data)
                .addOnSuccessListener {
                    auth.createUserWithEmailAndPassword(
                        data["email"] as String,
                        data["password"] as String
                    )
                        .addOnSuccessListener {
                            onSuccess()
                        }.addOnFailureListener {
                            onError(it)
                        }
                }.addOnFailureListener {
                    onError(it)
                }
        }
    }

    override suspend fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener(onError)
        }
    }

    companion object {
        private const val USER_REFERENCE = "users"
    }
}