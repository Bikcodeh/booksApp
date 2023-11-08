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
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    dispatcherProvider: DispatcherProvider
) : MVIViewModel<CategoryEvent>(dispatcherProvider) {

    var viewState by mutableStateOf(CategoryUiState())
    override fun handleEvents(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnDeleteCategory -> viewState = viewState.copy(showDeleteDialog = true)
            CategoryEvent.OnDeleteCategoryDismiss -> viewState =
                viewState.copy(showDeleteDialog = false)

            CategoryEvent.OnAddCategory -> viewState = viewState.copy(showAddEditDialog = true)
            CategoryEvent.OnAddCategoryDismiss -> viewState =
                viewState.copy(showAddEditDialog = false)

            is CategoryEvent.OnCategoryChange -> viewState =
                viewState.copy(category = event.text)

            is CategoryEvent.OnDelete -> handleOnDelete(event.onDeleteEvent)
            is CategoryEvent.OnAddEdit -> handleOnAddEdit(event.onAddEditCategoryEvent)
        }
    }

    private fun handleOnAddEdit(onAddEditCategoryEvent: OnAddEditCategoryEvent) {
        viewState = when (onAddEditCategoryEvent) {
            is OnAddEditCategoryEvent.Dialog -> viewState.copy(
                showAddEditDialog = true,
                isEditingCategory = onAddEditCategoryEvent.isEdit
            )

            is OnAddEditCategoryEvent.OnCancel -> viewState.copy(
                showAddEditDialog = false,
                isEditingCategory = false,
                category = ""
            )

            is OnAddEditCategoryEvent.OnConfirm -> viewState.copy(
                showAddEditDialog = false,
                isEditingCategory = false,
                category = ""
            )

            is OnAddEditCategoryEvent.OnDismiss -> viewState.copy(
                showAddEditDialog = false,
                isEditingCategory = false,
                category = ""
            )
        }
    }

    private fun handleOnDelete(onDeleteCategoryEvent: OnDeleteCategoryEvent) {
        viewState = when (onDeleteCategoryEvent) {
            is OnDeleteCategoryEvent.Dialog -> viewState.copy(showDeleteDialog = true)
            OnDeleteCategoryEvent.OnCancel -> viewState.copy(showDeleteDialog = false, category = "")
            OnDeleteCategoryEvent.OnConfirm -> viewState.copy(showDeleteDialog = false, category = "")
            OnDeleteCategoryEvent.OnDismiss -> viewState.copy(showDeleteDialog = false, category = "")
        }
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