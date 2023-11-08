package com.bikcode.booksapp.ui.screens.category.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.category.AddCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.EditCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.GetAllCategoriesUseCase
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val editCategoryUseCase: EditCategoryUseCase,
    dispatcherProvider: DispatcherProvider
) : MVIViewModel<CategoryEvent>(dispatcherProvider) {

    var viewState by mutableStateOf(CategoryUiState())
    override fun handleEvents(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnCategoryChange -> viewState =
                viewState.copy(category = event.text)

            is CategoryEvent.OnDelete -> handleOnDelete(event.onDeleteEvent)
            is CategoryEvent.OnAddEdit -> handleOnAddEdit(event.onAddEditCategoryEvent)
            is CategoryEvent.OnCategorySelected -> viewState =
                viewState.copy(categorySelected = event.categorySelected)
        }
    }

    private fun onEdit() {
        viewState.categorySelected?.let {
            editCategoryUseCase(
                category = it.copy(description = viewState.category),
                onSuccess = {
                    viewState =
                        viewState.copy(
                            error = null,
                            loading = false,
                            isEditingCategory = false,
                            category = "",
                            showAddEditDialog = false
                        )
                },
                onError = {
                    viewState = viewState.copy(
                        error = UiText.StringResource(
                            R.string.error_edit_category
                        ), loading = false
                    )
                }
            )
        }
    }

    private fun onAdd() {
        addCategoryUseCase(
            description = viewState.category,
            onSuccess = {
                viewState =
                    viewState.copy(
                        error = null,
                        loading = false,
                        isEditingCategory = false,
                        category = "",
                        showAddEditDialog = false
                    )
            },
            onError = {
                viewState = viewState.copy(
                    error = UiText.StringResource(
                        R.string.error_edit_category
                    ),
                    loading = false
                )
            }
        )
    }

    private fun handleOnAddEdit(event: OnAddEditCategoryEvent) {
        when (event) {
            is OnAddEditCategoryEvent.Dialog -> viewState = viewState.copy(
                showAddEditDialog = true,
                isEditingCategory = event.isEdit
            )

            is OnAddEditCategoryEvent.OnCancel -> viewState = viewState.copy(
                showAddEditDialog = false,
                isEditingCategory = false,
                category = "",
                categorySelected = null
            )

            is OnAddEditCategoryEvent.OnConfirm -> {
                if (viewState.isEditingCategory) onEdit() else onAdd()
            }

            is OnAddEditCategoryEvent.OnDismiss -> viewState = viewState.copy(
                showAddEditDialog = false,
                isEditingCategory = false,
                category = ""
            )
        }
    }

    private fun handleOnDelete(onDeleteCategoryEvent: OnDeleteCategoryEvent) {
        viewState = when (onDeleteCategoryEvent) {
            is OnDeleteCategoryEvent.Dialog -> viewState.copy(showDeleteDialog = true)
            OnDeleteCategoryEvent.OnCancel -> viewState.copy(
                showDeleteDialog = false,
                category = ""
            )

            OnDeleteCategoryEvent.OnConfirm -> viewState.copy(
                showDeleteDialog = false,
                category = ""
            )

            OnDeleteCategoryEvent.OnDismiss -> viewState.copy(
                showDeleteDialog = false,
                category = ""
            )
        }
    }

    init {
        viewState = viewState.copy(loading = true)
        getAllCategoriesUseCase(
            onError = {
                viewState = viewState.copy(
                    loading = false,
                    error = UiText.StringResource(R.string.error_fetching_categories)
                )
            },
            onSuccess = {
                viewState = viewState.copy(loading = false, categories = it)
            }
        )
    }
}