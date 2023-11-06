package com.bikcode.booksapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun ColumnScope.FormFieldStringPassword(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @StringRes placeholder: Int,
    formValue: String,
    onChangeValue: (String) -> Unit,
    error: UiText? = null
) {
    var focused by remember { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
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
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = error != null,
            trailingIcon = {
                val image = if (passwordVisible)
                    R.drawable.ic_visibility
                else R.drawable.ic_visibility_off

                val description =
                    if (passwordVisible) R.string.hide_password else R.string.show_password

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = painterResource(id = image), stringResource(id = description))
                }
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
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.formFieldColorUnfocused,
                focusedBorderColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.formFieldColorFocused
            ),
            shape = ShapeDefaults.Medium
        )
    }
    if (error != null) {
        Text(text = error.asString(), color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
    }
}

@Preview
@Composable
private fun FieldPreview() {
    Column {
        FormFieldStringPassword(
            label = R.string.name,
            placeholder = R.string.name_placeholder,
            formValue = "",
            onChangeValue = {}
        )
    }
}