package com.bikcode.booksapp.ui.screens.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.bikcode.booksapp.ui.components.CategoryBook
import com.bikcode.booksapp.ui.components.DialogLoading
import com.bikcode.booksapp.ui.components.Loading
import com.bikcode.booksapp.ui.screens.category.components.CategorySearch
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryContent(
    uiState: CategoryUiState
) {
    val localFocusManager = LocalFocusManager.current
    if (uiState.loading) {
        Loading()
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {
            stickyHeader { CategorySearch(modifier = Modifier.padding(top = 8.dp)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(uiState.categories, key = { it.description }) {
                CategoryBook(
                    modifier = Modifier.padding(vertical = 4.dp),
                    onEdit = {},
                    onDelete = { },
                    textValue = it.description
                )
            }
        }
    }
}