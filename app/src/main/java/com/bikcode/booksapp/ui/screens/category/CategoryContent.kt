package com.bikcode.booksapp.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.eventbus.events.CategoryFab
import com.bikcode.booksapp.core.eventbus.events.EventBusEvent
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.model.Category
import com.bikcode.booksapp.ui.components.CategoryBook
import com.bikcode.booksapp.ui.components.DialogConfirm
import com.bikcode.booksapp.ui.components.DialogField
import com.bikcode.booksapp.ui.components.Loading
import com.bikcode.booksapp.ui.screens.category.components.CategorySearch
import com.bikcode.booksapp.ui.screens.category.viewmodel.CategoryUiState
import com.bikcode.booksapp.ui.screens.category.viewmodel.OnAddEditCategoryEvent
import com.bikcode.booksapp.ui.screens.category.viewmodel.OnDeleteCategoryEvent

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryContent(
    uiState: CategoryUiState,
    showSnackBar: (String) -> Unit,
    paddingValues: PaddingValues,
    handleOnDelete: (OnDeleteCategoryEvent) -> Unit,
    handleOnAddEdit: (OnAddEditCategoryEvent) -> Unit,
    onCategoryChange: (String) -> Unit,
    onCategorySelected: (Category) -> Unit,
    onFilter: (String) -> Unit,
    onClearFilter: () -> Unit,
    dispatchEventBusEvent: (EventBusEvent) -> Unit,
    lifecycleOwner: LifecycleOwner
) {
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    uiState.error?.let {
        showSnackBar(it.toString())
    }
    LaunchedEffect(key1 = uiState.loading) {
        dispatchEventBusEvent(CategoryFab(show = !uiState.loading) {
            handleOnAddEdit(OnAddEditCategoryEvent.Dialog(false))
        })
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                dispatchEventBusEvent(CategoryFab(show = false) {})
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (uiState.loading) {
        Loading()
    } else {
        if (uiState.showDeleteDialog) {
            DialogConfirm(
                titleText = UiText.StringResource(R.string.delete_category_title)
                    .asString(context),
                bodyText = UiText.StringResource(
                    R.string.confirm_delete_category_text,
                    uiState.category
                )
                    .asString(context),
                confirmButtonText = UiText.StringResource(R.string.confirm_delete)
                    .asString(context),
                cancelButtonText = UiText.StringResource(R.string.cancel)
                    .asString(context),
                onConfirm = { handleOnDelete(OnDeleteCategoryEvent.OnConfirm) },
                onCancel = { handleOnDelete(OnDeleteCategoryEvent.OnCancel) },
                onDismiss = { handleOnDelete(OnDeleteCategoryEvent.OnDismiss) }
            )
        }
        if (uiState.showAddEditDialog) {
            DialogField(
                titleText = stringResource(id = if (uiState.isEditingCategory) R.string.edit_category else R.string.add_category),
                formText = uiState.category,
                formLabel = null,
                formPlaceholder = R.string.category_placeholder,
                onTextChange = { onCategoryChange(it) },
                confirmButtonText = stringResource(id = if (uiState.isEditingCategory) R.string.edit_option else R.string.add_option),
                onConfirm = { handleOnAddEdit(OnAddEditCategoryEvent.OnConfirm) },
                onCancel = { handleOnAddEdit(OnAddEditCategoryEvent.OnCancel) },
                cancelButtonText = stringResource(id = R.string.cancel),
                onDismiss = { handleOnAddEdit(OnAddEditCategoryEvent.OnDismiss) }
            )
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(end = 16.dp, start = 16.dp, top = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
        ) {
            stickyHeader {
                CategorySearch(
                    modifier = Modifier.padding(top = 8.dp),
                    onTextChange = onFilter,
                    onClearFilter = onClearFilter
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(
                items = uiState.filteredCategories ?: uiState.categories,
                key = { it.description }
            ) {
                CategoryBook(
                    modifier = Modifier.padding(vertical = 4.dp),
                    onEdit = {
                        onCategoryChange(it.description)
                        onCategorySelected(it)
                        handleOnAddEdit(OnAddEditCategoryEvent.Dialog(true))
                    },
                    onDelete = {
                        onCategorySelected(it)
                        handleOnDelete(OnDeleteCategoryEvent.Dialog)
                    },
                    textValue = it.description
                )
            }
        }
    }
}

@Preview
@Composable
private fun CategoryContentPreview() {
    CategoryContent(
        uiState = CategoryUiState(loading = false),
        paddingValues = PaddingValues(),
        handleOnAddEdit = {},
        showSnackBar = {},
        handleOnDelete = {},
        onCategoryChange = {},
        onCategorySelected = {},
        onClearFilter = {},
        onFilter = {},
        dispatchEventBusEvent = {},
        lifecycleOwner = LocalLifecycleOwner.current
    )
}