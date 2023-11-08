package com.bikcode.booksapp.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.ui.components.CategoryBook
import com.bikcode.booksapp.ui.components.DialogConfirm
import com.bikcode.booksapp.ui.components.Loading
import com.bikcode.booksapp.ui.screens.category.components.CategorySearch
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryUiState

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryContent(
    uiState: CategoryUiState,
    paddingValues: PaddingValues,
    onDeleteCategoryClick: () -> Unit,
    onDeleteDismiss: () -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    var showFab by remember { mutableStateOf(false) }
    var categorySelected by remember { mutableStateOf<Category?>(null) }
    LaunchedEffect(key1 = uiState.loading) {
        if (!uiState.loading) showFab = true
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFab,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FloatingActionButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        if (uiState.loading) {
            Loading()
        } else {
            if (uiState.showDeleteDialog) {
                DialogConfirm(
                    titleText = UiText.StringResource(R.string.delete_category_title)
                        .asString(context),
                    bodyText = UiText.StringResource(
                        R.string.confirm_delete_category_text,
                        categorySelected?.description.orEmpty()
                    )
                        .asString(context),
                    confirmButtonText = UiText.StringResource(R.string.confirm_delete)
                        .asString(context),
                    cancelButtonText = UiText.StringResource(R.string.cancel)
                        .asString(context),
                    onConfirm = { onDeleteDismiss() },
                    onCancel = { onDeleteDismiss() },
                    onDismiss = { onDeleteDismiss() }
                )
            }
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
                        onEdit = {
                            categorySelected = it
                        },
                        onDelete = {
                            categorySelected = it
                            onDeleteCategoryClick()
                        },
                        textValue = it.description
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyCategory() {

}

@Preview
@Composable
private fun CategoryContentPreview() {
    CategoryContent(
        uiState = CategoryUiState(),
        paddingValues = PaddingValues(),
        onDeleteCategoryClick = {},
        onDeleteDismiss = {}
    )
}