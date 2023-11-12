package com.bikcode.booksapp.ui.screens.account.ui.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.components.ButtonBack
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.account.ui.viewmodel.ChangePasswordUiState
import com.bikcode.booksapp.ui.utils.extension.clearFocusOnClickOutside

@Composable
fun ChangePasswordContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    currentPassword: String?,
    onChangePassword: (String) -> Unit,
    onChangeConfirmPassword: (String) -> Unit,
    uiState: ChangePasswordUiState
) {
    val requestFocus = LocalFocusManager.current
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clearFocusOnClickOutside(requestFocus)
    ) {
        val (back, form, button, title) = createRefs()
        ButtonBack(
            modifier = Modifier.constrainAs(back) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }
        ) {
            onBack()
        }
        Text(
            text = stringResource(id = R.string.change_password),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(back.top)
                bottom.linkTo(back.bottom)
                end.linkTo(parent.end, 16.dp)
                start.linkTo(parent.start)
            }
        )
        Column(
            modifier = Modifier.constrainAs(form) {
                linkTo(parent.start, parent.end, startMargin = 16.dp, endMargin = 16.dp)
                top.linkTo(title.bottom, 60.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            FormFieldStringPassword(
                isEnabled = false,
                label = R.string.current_password,
                placeholder = R.string.password_placeholder,
                formValue = currentPassword.orEmpty(),
                onChangeValue = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            FormFieldStringPassword(
                label = R.string.new_password,
                placeholder = R.string.password_placeholder,
                formValue = uiState.password,
                onChangeValue = { onChangePassword(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            FormFieldStringPassword(
                label = R.string.confirm_new_password,
                placeholder = R.string.password_placeholder,
                formValue = uiState.confirmPassword,
                onChangeValue = { onChangeConfirmPassword(it) }
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth(), shape = ShapeDefaults.Medium) {
                Text(text = stringResource(id = R.string.update_password).uppercase())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChangePasswordPreview() {
    //ChangePasswordContent
}