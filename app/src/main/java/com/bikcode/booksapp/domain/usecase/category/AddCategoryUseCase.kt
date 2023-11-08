package com.bikcode.booksapp.domain.usecase.category

import com.bikcode.booksapp.domain.repository.CategoryRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(
        description: String,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        categoryRepository.addCategory(description, onSuccess, onError)
    }
}