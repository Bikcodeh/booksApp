package com.bikcode.booksapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.ui.theme.formFieldColorFocused
import com.bikcode.booksapp.ui.theme.formFieldColorUnfocused
import com.bikcode.booksapp.ui.theme.formTextColor

@Composable
fun ColumnScope.FormFieldString(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @StringRes placeholder: Int,
    formValue: String,
    onChangeValue: (String) -> Unit,
    hasError: UiText? = null
) {
    var focused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val shadowDp: Dp by animateDpAsState(
        targetValue = if (focused) 4.dp else 0.dp,
        animationSpec = tween(durationMillis = 400),
        label = label.toString()
    )
    Text(text = stringResource(id = label), color = MaterialTheme.colorScheme.formTextColor)
    Spacer(modifier = Modifier.height(4.dp))
    Surface(
        shadowElevation = shadowDp,
        shape = ShapeDefaults.Medium
    ) {
        OutlinedTextField(
            placeholder = {
                Text(
                    text = stringResource(id = placeholder),
                    color = MaterialTheme.colorScheme.formTextColor.copy(alpha = 0.2f)
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusEvent {
                    focused = it.isFocused
                },
            value = formValue,
            onValueChange = { onChangeValue(it) },
            singleLine = true,
            maxLines = 1,
            isError = hasError != null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.formFieldColorUnfocused,
                focusedBorderColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.formFieldColorFocused,
                errorContainerColor = MaterialTheme.colorScheme.formFieldColorFocused
            ),
            shape = ShapeDefaults.Medium
        )
    }
    if (hasError != null) {
        Text(text = hasError.asString(), color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
    }
}

@Preview
@Composable
private fun FieldPreview() {
    Column {
        FormFieldString(
            label = R.string.name,
            placeholder = R.string.name_placeholder,
            formValue = "",
            onChangeValue = {},
            hasError = UiText.StringResource(R.string.name)
        )
    }
}