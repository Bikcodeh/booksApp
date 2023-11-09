package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override fun doSignUp(
        data: HashMap<String, Any>,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        FirebaseFirestore.getInstance().collection(USER_REFERENCE).add(data)
            .addOnSuccessListener {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
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

    override fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener(onError)
    }

    companion object {
        private const val USER_REFERENCE = "users"
    }
}