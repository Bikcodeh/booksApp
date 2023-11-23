package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.model.User
import com.bikcode.booksapp.domain.repository.AuthRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    override suspend fun doSignUp(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val data = mutableMapOf<String, Any>().apply {
            put(NAME_KEY, name)
            put(EMAIL_KEY, email)
            put(PASSWORD_KEY, password)
            put(ROLE_ID_KEY, ROLE_USER_ID)
        }
        withContext(dispatcherProvider.io) {
            auth.createUserWithEmailAndPassword(
                data[EMAIL_KEY] as String,
                data[PASSWORD_KEY] as String
            ).addOnSuccessListener {
                data[UID_KEY] = it.user?.uid!!
                db.collection(USER_REFERENCE).add(data)
                        .addOnSuccessListener {
                            onSuccess()
                        }.addOnFailureListener { error ->
                            onError(error)
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

    override suspend fun getAuthUser(
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            auth.currentUser?.let { loggedUser ->
                db.collection(USER_REFERENCE).whereEqualTo(EMAIL_KEY, loggedUser.email)
                    .get()
                    .addOnSuccessListener {
                        val userMapData = it.documents[0].data
                        userMapData?.let { dataUser ->
                            val jsonData = Gson().toJson(dataUser)
                            val user = Gson().fromJson(jsonData, User::class.java)
                            onSuccess(user)
                        } ?: run {
                            onError(Throwable())
                        }
                    }.addOnFailureListener {
                        onError(it)
                    }
            }
        }
    }

    companion object {
        private const val USER_REFERENCE = "users"
        private const val ROLE_USER_ID = "Lwi3O8OEYk70sxSM1r0r"
        private const val NAME_KEY = "name"
        private const val UID_KEY = "uid"
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
        private const val ROLE_ID_KEY = "roleId"
    }
}