package com.bikcode.booksapp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bikcode.booksapp.R
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.components.DialogLoading
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginUiState

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
    loginUiState: LoginUiState,
    onLogin: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    LaunchedEffect(key1 = loginUiState.goToHome) {
        if (loginUiState.goToHome) {
            navigate(Screens.Home.route)
        }
    }

    if (loginUiState.showLoading) {
        DialogLoading()
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (form, title, image, signup) = createRefs()
        Text(
            text = stringResource(id = R.string.login_title),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top, 40.dp)
                    start.linkTo(parent.start)
                },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.login_amico),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(image) {
                    top.linkTo(title.bottom, 40.dp)
                    start.linkTo(parent.start)
                }
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(form) {
                top.linkTo(image.bottom, 44.dp)
                linkTo(parent.start, parent.end)
            }
        ) {
            FormFieldString(
                label = R.string.email,
                placeholder = R.string.email_placeholder,
                formValue = loginUiState.email,
                onChangeValue = onEmailChange,
                hasError = loginUiState.emailError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = loginUiState.password,
                onChangeValue = onPasswordChange,
                error = loginUiState.passwordError
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = { onLogin() },
                shape = ShapeDefaults.Small
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.login_btn).uppercase(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        TextButton(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(signup) {
                linkTo(form.bottom, parent.bottom, bias = 1f)
            },
            onClick = { }
        ) {
            Text(
                text = stringResource(id = R.string.signup_link),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    LoginContent(
        navigate = {},
        loginUiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onLogin = {}
    )
}