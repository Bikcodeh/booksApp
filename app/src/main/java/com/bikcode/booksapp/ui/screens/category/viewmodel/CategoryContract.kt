package com.bikcode.booksapp.ui.screens.category.viewmodel

import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.ui.utils.ViewEvent
import com.bikcode.booksapp.ui.utils.ViewSideEffect
import com.bikcode.booksapp.ui.utils.ViewState

sealed class CategoryEffect: ViewSideEffect {
    data class Loading(val show: Boolean): CategoryEffect()
}
sealed class CategoryEvent: ViewEvent {

}

data class CategoryUiState(
    val categories: List<Category> = emptyList(),
    val error: Boolean = false,
    val loading: Boolean = false
): ViewState