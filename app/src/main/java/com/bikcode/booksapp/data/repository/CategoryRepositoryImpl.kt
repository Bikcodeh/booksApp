package com.bikcode.booksapp.data.repository

import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override fun getAllCategories(
        onSuccess: (categories: List<Category>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Firebase.firestore.collection(CATEGORY_REFERENCE).get()
            .addOnSuccessListener {
                val data = mutableListOf<Category>()
                if (!it.isEmpty) {
                    it.documents.asSequence().forEach { document ->
                        val jsonData = Gson().toJson(document.data)
                        data.add(Gson().fromJson(jsonData, Category::class.java))
                        onSuccess(data)
                    }
                } else {
                    onSuccess(emptyList())
                }
            }
            .addOnFailureListener(onError)
    }

    companion object {
        private const val CATEGORY_REFERENCE = "categories"
    }
}