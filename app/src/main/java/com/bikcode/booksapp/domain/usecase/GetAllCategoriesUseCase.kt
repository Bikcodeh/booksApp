package com.bikcode.booksapp.domain.usecase

import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    operator fun invoke(
        onSuccess: (categories: List<Category>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        categoryRepository.getAllCategories(onSuccess, onError)
    }
}