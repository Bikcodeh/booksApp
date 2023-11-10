package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override suspend fun doSignUp(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val data = hashMapOf<String, Any>().apply {
            put(NAME_KEY, name)
            put(EMAIL_KEY, email)
            put(PASSWORD_KEY,password)
            put(ROLE_ID_KEY, ROLE_USER_ID)
        }
        withContext(dispatcherProvider.io) {
            FirebaseFirestore.getInstance().collection(USER_REFERENCE).add(data)
                .addOnSuccessListener {
                    auth.createUserWithEmailAndPassword(
                        data[EMAIL_KEY] as String,
                        data[PASSWORD_KEY] as String
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
        private const val ROLE_USER_ID = "Lwi3O8OEYk70sxSM1r0r"
        private const val NAME_KEY = "name"
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
        private const val ROLE_ID_KEY = "roleId"
    }
}