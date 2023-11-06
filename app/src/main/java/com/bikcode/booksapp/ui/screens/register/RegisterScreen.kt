package com.bikcode.booksapp.ui.screens.register

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val nameForm = remember { mutableStateOf("") }
        val emailForm = remember { mutableStateOf("") }
        val passwordForm = remember { mutableStateOf("") }
        val confirmPasswordForm = remember { mutableStateOf("") }
        val (backButton, title, form) = createRefs()
        IconButton(modifier = Modifier
            .size(24.dp)
            .constrainAs(backButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }, onClick = { onBack() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(backButton.bottom, 34.dp)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(form) {
                top.linkTo(title.bottom, 34.dp)
                width = Dimension.matchParent
            }
        ) {
            FormFieldString(
                label = R.string.name,
                placeholder = R.string.name_placeholder,
                formValue = nameForm,
                onChangeValue = { nameForm.value = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldString(
                label = R.string.email,
                placeholder = R.string.email_placeholder,
                formValue = emailForm,
                onChangeValue = { emailForm.value = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = passwordForm,
                onChangeValue = { passwordForm.value = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password_confirm,
                placeholder = R.string.password_placeholder,
                formValue = confirmPasswordForm,
                onChangeValue = { confirmPasswordForm.value = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = {},
                shape = ShapeDefaults.Small
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sign_up).uppercase(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
fun RegisterScreenPreviewDark() {
    RegisterScreen(onBack = {})
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onBack = {})
}