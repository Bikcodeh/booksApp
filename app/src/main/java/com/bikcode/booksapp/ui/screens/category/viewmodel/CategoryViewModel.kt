package com.bikcode.booksapp.ui.screens.category.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.GetAllCategoriesUseCase
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    dispatcherProvider: DispatcherProvider
) : MVIViewModel<CategoryEffect, CategoryEvent>(dispatcherProvider) {

    var viewState by mutableStateOf(CategoryUiState())
    override fun handleEvents(event: CategoryEvent) {

    }

    init {
        viewState = viewState.copy(loading = true)
        getAllCategoriesUseCase(
            onError = {
                viewState = viewState.copy(loading = false, error = true)
            },
            onSuccess = {
                viewState = viewState.copy(loading = false, categories = it)
            }
        )
    }
}