package com.bikcode.booksapp.ui.screens.category

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    CategoryContent(uiState = categoryViewModel.viewState)
}