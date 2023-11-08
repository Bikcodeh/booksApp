package com.bikcode.booksapp.ui.screens.category.viewmodel

import androidx.compose.runtime.Immutable
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.ui.utils.ViewEvent
import com.bikcode.booksapp.ui.utils.ViewState

sealed class CategoryEvent: ViewEvent {
    object  OnDeleteCategory: CategoryEvent()
    object  OnDeleteCategoryDismiss: CategoryEvent()
}

@Immutable
data class CategoryUiState(
    val categories: List<Category> = emptyList(),
    val error: Boolean = false,
    val loading: Boolean = true,
    val showDeleteDialog: Boolean = false
): ViewState