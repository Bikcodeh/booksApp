package com.bikcode.booksapp.domain.repository

import com.bikcode.booksapp.domain.commons.Result
import com.bikcode.booksapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<Result<List<Category>>>

    fun addCategory(
        category: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    fun editCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )

    fun deleteCategory(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    )
}