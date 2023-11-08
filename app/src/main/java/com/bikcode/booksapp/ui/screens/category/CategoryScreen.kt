package com.bikcode.booksapp.ui.screens.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryEvent
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    paddingValues: PaddingValues,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    CategoryContent(
        uiState = categoryViewModel.viewState,
        paddingValues = paddingValues,
        handleOnDelete = { categoryViewModel.handleEvents(CategoryEvent.OnDelete(it)) },
        handleOnAddEdit = { categoryViewModel.handleEvents(CategoryEvent.OnAddEdit(it)) },
        onCategoryChange = { categoryViewModel.handleEvents(CategoryEvent.OnCategoryChange(it)) }
    )
}