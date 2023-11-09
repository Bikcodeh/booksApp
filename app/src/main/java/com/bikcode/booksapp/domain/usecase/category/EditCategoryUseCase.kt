package com.bikcode.booksapp.domain.usecase.category

import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import javax.inject.Inject

class EditCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        category: Category,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        categoryRepository.editCategory(category, onSuccess, onError)
    }
}