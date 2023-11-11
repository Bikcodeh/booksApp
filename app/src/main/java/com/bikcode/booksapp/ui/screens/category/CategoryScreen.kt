package com.bikcode.booksapp.ui.screens.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.bikcode.booksapp.core.eventbus.EventBusViewModel
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryEvent
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    paddingValues: PaddingValues,
    showSnackBar: (String) -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    eventBusViewModel: EventBusViewModel = hiltViewModel()
) {
    CategoryContent(
        uiState = categoryViewModel.viewState,
        paddingValues = paddingValues,
        handleOnDelete = { categoryViewModel.handleEvents(CategoryEvent.OnDelete(it)) },
        handleOnAddEdit = { categoryViewModel.handleEvents(CategoryEvent.OnAddEdit(it)) },
        onCategoryChange = { categoryViewModel.handleEvents(CategoryEvent.OnCategoryChange(it)) },
        onCategorySelected = { categoryViewModel.handleEvents(CategoryEvent.OnCategorySelected(it)) },
        onFilter = { categoryViewModel.handleEvents(CategoryEvent.OnFilter(it)) },
        onClearFilter = { categoryViewModel.handleEvents(CategoryEvent.OnClearFilter) },
        dispatchEventBusEvent = { eventBusViewModel.sendEvent(it) },
        lifecycleOwner = lifecycleOwner,
        showSnackBar = showSnackBar
    )
}