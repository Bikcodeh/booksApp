package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.commons.Failure
import com.bikcode.booksapp.domain.commons.Result
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : CategoryRepository {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    override fun getAllCategories(): Flow<Result<List<Category>>> = callbackFlow {
        val listener = db.collection(CATEGORY_REFERENCE)
            .addSnapshotListener { snapshots, error ->
            if (error != null) {
                trySend(Result.Error(Failure.UnknownException()))
                return@addSnapshotListener
            }

            if (!snapshots!!.isEmpty) {
                val data = mutableListOf<Category>()
                snapshots.documents.asSequence().forEach { document ->
                    val jsonData = Gson().toJson(document.data)
                    data.add(Gson().fromJson(jsonData, Category::class.java))
                }
                trySend(Result.Success(data))
            } else {
                trySend(Result.Success(emptyList()))
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun addCategory(
        category: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val data = hashMapOf<String, Any>().apply {
            put(UID_KEY, UUID.randomUUID().toString())
            put(DESCRIPTION_KEY, category)
        }
        withContext(dispatcherProvider.io) {
            db.collection(CATEGORY_REFERENCE).add(data)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener(onError)
        }
    }

    override suspend fun editCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            db.collection(CATEGORY_REFERENCE)
                .whereEqualTo(UID_KEY, category.uid)
                .get()
                .addOnSuccessListener {
                    val categoryToEdit = it.documents[0]
                    db.collection(CATEGORY_REFERENCE)
                        .document(categoryToEdit.id)
                        .update(DESCRIPTION_KEY, category.description)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener(onError)
                }
                .addOnFailureListener(onError)
        }
    }

    override suspend fun deleteCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        withContext(dispatcherProvider.io) {
            db.collection(CATEGORY_REFERENCE)
                .whereEqualTo(UID_KEY, category.uid)
                .get()
                .addOnSuccessListener {
                    val categoryToDelete = it.documents[0]
                    db.collection(CATEGORY_REFERENCE)
                        .document(categoryToDelete.id)
                        .delete()
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener(onError)
                }.addOnFailureListener(onError)
        }
    }

    companion object {
        private const val CATEGORY_REFERENCE = "categories"
        private const val UID_KEY = "uid"
        private const val DESCRIPTION_KEY = "description"
    }
}