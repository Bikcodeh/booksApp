package com.bikcode.booksapp.ui.screens.signup

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.components.DialogLoading
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpUiState

@Composable
fun SignUpContent(
    onBack: () -> Unit,
    navigate: (String) -> Unit,
    signUpUiState: SignUpUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(key1 = signUpUiState.goHome) {
        if (signUpUiState.goHome) {
            navigate(Screens.Home.route)
        }
    }
    if (signUpUiState.showLoading) {
        DialogLoading()
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {

        val (backButton, title, form, image) = createRefs()
        IconButton(modifier = Modifier
            .size(24.dp)
            .constrainAs(backButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }, onClick = { onBack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .constrainAs(image) {
                    top.linkTo(title.bottom)
                },
            painter = painterResource(id = R.drawable.sign_up_amico),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(backButton.bottom, 8.dp)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(form) {
                top.linkTo(image.bottom, 14.dp)
                width = Dimension.matchParent
            }
        ) {
            FormFieldString(
                label = R.string.name,
                placeholder = R.string.name_placeholder,
                formValue = signUpUiState.name,
                onChangeValue = onNameChange,
                hasError = signUpUiState.nameError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldString(
                label = R.string.email,
                placeholder = R.string.email_placeholder,
                formValue = signUpUiState.email,
                onChangeValue = onEmailChange,
                hasError = signUpUiState.emailError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = signUpUiState.password,
                onChangeValue = onPasswordChange,
                error = signUpUiState.passwordError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password_confirm,
                placeholder = R.string.password_placeholder,
                formValue = signUpUiState.confirmPassword,
                onChangeValue = onConfirmPasswordChange,
                error = signUpUiState.confirmPasswordError
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = onSignUp,
                shape = ShapeDefaults.Small
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sign_up).uppercase(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
private fun SignUpContentPreviewDark() {
    SignUpContent(onBack = {},
        navigate = {},
        signUpUiState = SignUpUiState(
            name = "Adrienne James",
            email = "abby.santiago@example.com",
            password = "conubia",
            confirmPassword = "nulla",
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            showLoading = false,
            goHome = false
        ),
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onSignUp = {})
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
private fun SignUpContentPreview() {
    SignUpContent(onBack = {},
        navigate = {},
        signUpUiState = SignUpUiState(
            name = "Shelby Bernard",
            email = "marva.phelps@example.com",
            password = "epicurei",
            confirmPassword = "eloquentiam",
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            showLoading = false,
            goHome = false
        ),
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onSignUp = {})
}