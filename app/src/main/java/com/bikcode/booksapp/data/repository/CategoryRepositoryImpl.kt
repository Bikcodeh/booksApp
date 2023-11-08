package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.util.UUID
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override fun getAllCategories(
        onSuccess: (categories: List<Category>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Firebase.firestore.collection(CATEGORY_REFERENCE).addSnapshotListener { snapshots, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            if (!snapshots!!.isEmpty) {
                val data = mutableListOf<Category>()
                snapshots.documents.asSequence().forEach { document ->
                    val jsonData = Gson().toJson(document.data)
                    data.add(Gson().fromJson(jsonData, Category::class.java))
                }
                onSuccess(data)
            } else {
                onSuccess(emptyList())
            }
        }
    }

    override fun addCategory(
        category: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val data = hashMapOf<String, Any>().apply {
            put(UID_KEY, UUID.randomUUID().toString())
            put(DESCRIPTION_KEY, category)
        }
        Firebase.firestore.collection(CATEGORY_REFERENCE).add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onError)
    }

    override fun editCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val db = Firebase.firestore
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

    override fun deleteCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val db = Firebase.firestore
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

    companion object {
        private const val CATEGORY_REFERENCE = "categories"
        private const val UID_KEY = "uid"
        private const val DESCRIPTION_KEY = "description"
    }
}