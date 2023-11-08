package com.bikcode.booksapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogConfirm(
    modifier: Modifier = Modifier,
    titleText: String,
    bodyText: String,
    confirmButtonText: String,
    cancelButtonText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    var show by rememberSaveable { mutableStateOf(true) }

    AlertDialog(
        modifier = modifier,
        title = { Text(text = titleText) },
        text = { Text(text = bodyText) },
        onDismissRequest = {
            show = false
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onCancel()
                onDismiss()
            }) {
                Text(text = cancelButtonText)
            }
        }
    )
}

@Preview
@Composable
private fun DialogConfirmPreview() {
    DialogConfirm(
        onConfirm = {},
        onCancel = {},
        onDismiss = {},
        titleText = "conubia",
        bodyText = "wisi",
        confirmButtonText = "euripidis",
        cancelButtonText = "oratio"

    )
}