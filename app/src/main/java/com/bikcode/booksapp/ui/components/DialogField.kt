package com.bikcode.booksapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.theme.backgroundColor
import com.bikcode.booksapp.ui.theme.formTextColor


@Composable
fun DialogField(
    modifier: Modifier = Modifier,
    titleText: String,
    formText: String,
    @StringRes formLabel: Int? = null,
    @StringRes formPlaceholder: Int,
    onTextChange: (String) -> Unit,
    confirmButtonText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    cancelButtonText: String,
    onDismiss: () -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp
    Dialog(
        onDismissRequest = { onDismiss() },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .width((width * 0.8).dp)
                .height(220.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val (title, field, buttons) = createRefs()
                Text(
                    text = titleText,
                    modifier = Modifier.constrainAs(title) {
                        linkTo(parent.start, parent.end)
                        top.linkTo(parent.top)
                        this.width = Dimension.fillToConstraints
                    },
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.formTextColor
                )
                Column(modifier = Modifier.constrainAs(field) {
                    linkTo(parent.start, parent.end)
                    this.width = Dimension.fillToConstraints
                    top.linkTo(title.bottom, 24.dp)
                }) {
                    FormFieldString(
                        label = formLabel,
                        placeholder = formPlaceholder,
                        formValue = formText,
                        onChangeValue = onTextChange
                    )
                }
                Row(modifier = Modifier.constrainAs(buttons) {
                    end.linkTo(parent.end)
                    top.linkTo(field.bottom, 24.dp)
                }) {
                    OutlinedButton(onClick = { onCancel() }, modifier = Modifier.weight(1f)) {
                        Text(
                            text = cancelButtonText.uppercase(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { onConfirm() }, modifier = Modifier.weight(1f)) {
                        Text(
                            text = confirmButtonText.uppercase(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DialogFieldPreview() {
    DialogField(
        titleText = "Add Category",
        formText = "",
        formLabel = null,
        formPlaceholder = R.string.add_option,
        onTextChange = {},
        confirmButtonText = "Add",
        cancelButtonText = "Cancel",
        onDismiss = {},
        onConfirm = {},
        onCancel = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DialogFieldPreviewDark() {
    DialogField(
        titleText = "Add Category",
        formText = "",
        formLabel = null,
        formPlaceholder = R.string.add_option,
        onTextChange = {},
        confirmButtonText = "Add",
        cancelButtonText = "Cancel",
        onDismiss = {},
        onConfirm = {},
        onCancel = {}
    )
}