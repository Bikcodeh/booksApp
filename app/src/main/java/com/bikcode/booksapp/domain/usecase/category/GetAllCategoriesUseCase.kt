package com.bikcode.booksapp.domain.usecase.category

import com.bikcode.booksapp.domain.commons.Result
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<Result<List<Category>>> = categoryRepository.getAllCategories()
}