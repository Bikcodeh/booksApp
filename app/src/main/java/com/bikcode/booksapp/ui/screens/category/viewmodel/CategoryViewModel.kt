package com.bikcode.booksapp.ui.screens.category.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.commons.Result
import com.bikcode.booksapp.domain.repository.DispatcherProvider
import com.bikcode.booksapp.domain.usecase.category.AddCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.DeleteCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.EditCategoryUseCase
import com.bikcode.booksapp.domain.usecase.category.GetAllCategoriesUseCase
import com.bikcode.booksapp.ui.utils.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val editCategoryUseCase: EditCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val dispatcherProvider: DispatcherProvider
) : MVIViewModel<CategoryEvent>() {

    var viewState by mutableStateOf(CategoryUiState())
    override fun handleEvents(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OnCategoryChange -> viewState =
                viewState.copy(category = event.text)

            is CategoryEvent.OnDelete -> handleOnDelete(event.onDeleteEvent)
            is CategoryEvent.OnAddEdit -> handleOnAddEdit(event.onAddEditCategoryEvent)
            is CategoryEvent.OnCategorySelected -> viewState =
                viewState.copy(categorySelected = event.categorySelected)

            is CategoryEvent.OnFilter -> onFilter(event.text)
            CategoryEvent.OnClearFilter -> viewState =
                viewState.copy(filteredCategories = null, textFilterCategories = "")
        }
    }

    private fun onFilter(text: String) {
        viewState = viewState.copy(textFilterCategories = text)
        viewState = viewState.copy(
            filteredCategories = viewState.categories.filter {
                it.description.contains(
                    text,
                    ignoreCase = true
                )
            }
        )
    }

    private fun onEdit() {
        viewState.categorySelected?.let {
            viewModelScope.launch(dispatcherProvider.io) {
                editCategoryUseCase(
                    category = it.copy(description = viewState.category),
                    onSuccess = {
                        viewState =
                            viewState.copy(
                                error = null,
                                loading = false,
                                isEditingCategory = false,
                                category = "",
                                showAddEditDialog = false,
                                filteredCategories = if (viewState.filteredCategories != null) {
                                    onFilter(viewState.textFilterCategories)
                                    viewState.filteredCategories
                                } else {
                                    null
                                }
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
    }

    private fun onAdd() {
        viewModelScope.launch(dispatcherProvider.io) {
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
    }

    private fun onDelete() {
        viewState.categorySelected?.let {
            viewModelScope.launch(dispatcherProvider.io) {
                deleteCategoryUseCase.invoke(
                    it,
                    onSuccess = {
                        viewState =
                            viewState.copy(
                                error = null,
                                loading = false,
                                isEditingCategory = false,
                                category = "",
                                showAddEditDialog = false,
                                categorySelected = null
                            )
                    },
                    onError = {
                        viewState = viewState.copy(
                            error = UiText.StringResource(
                                R.string.error_delete_category
                            ), loading = false
                        )
                    }
                )
            }
        }
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

            is OnDeleteCategoryEvent.OnConfirm -> {
                onDelete()
                viewState
            }

            OnDeleteCategoryEvent.OnDismiss -> viewState.copy(
                showDeleteDialog = false,
                category = ""
            )
        }
    }

    init {
        viewState = viewState.copy(loading = true)
        viewModelScope
        getAllCategoriesUseCase().onEach { result ->
            viewState = when (result) {
                is Result.Error -> viewState.copy(
                    loading = false,
                    error = UiText.StringResource(R.string.error_fetching_categories)
                )

                is Result.Success -> viewState.copy(loading = false, categories = result.data)
            }
        }.launchIn(viewModelScope)
    }
}