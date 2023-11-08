package com.bikcode.booksapp.ui.screens.category.viewmodel

import androidx.compose.runtime.Immutable
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.ui.utils.ViewEvent
import com.bikcode.booksapp.ui.utils.ViewState

sealed class CategoryEvent: ViewEvent {
    data class OnCategorySelected(val categorySelected: Category): CategoryEvent()
    data class OnCategoryChange(val text: String): CategoryEvent()
    data class OnDelete(val onDeleteEvent: OnDeleteCategoryEvent): CategoryEvent()
    data class OnAddEdit(val onAddEditCategoryEvent: OnAddEditCategoryEvent): CategoryEvent()
}
sealed class OnDeleteCategoryEvent {
    object Dialog: OnDeleteCategoryEvent()
    object OnConfirm: OnDeleteCategoryEvent()
    object OnCancel: OnDeleteCategoryEvent()
    object OnDismiss: OnDeleteCategoryEvent()
}

sealed class OnAddEditCategoryEvent {
    data class Dialog(val isEdit: Boolean): OnAddEditCategoryEvent()
    object OnConfirm: OnAddEditCategoryEvent()
    object OnCancel: OnAddEditCategoryEvent()
    object OnDismiss: OnAddEditCategoryEvent()
}

@Immutable
data class CategoryUiState(
    val categories: List<Category> = emptyList(),
    val error: UiText? = null,
    val loading: Boolean = true,
    val category: String = "",
    val categorySelected: Category? = null,
    val showDeleteDialog: Boolean = false,
    val showAddEditDialog: Boolean = false,
    val isEditingCategory: Boolean = false
): ViewState