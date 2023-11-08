package com.bikcode.booksapp.domain.repository

import com.bikcode.booksapp.domain.model.Category

interface CategoryRepository {
    fun getAllCategories(
        onSuccess: (categories: List<Category>) -> Unit,
        onError: (Throwable) -> Unit
    )
}