package com.bikcode.booksapp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bikcode.booksapp.R
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginEffect
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginEvent
import com.bikcode.booksapp.ui.screens.login.viewmodel.LoginViewModel
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpEffect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    val effects by loginViewModel.effects.receiveAsFlow()
        .collectAsStateWithLifecycle(initialValue = LoginEffect.Loading(false))
    when (effects) {
        is LoginEffect.Loading -> {
            if ((effects as LoginEffect.Loading).show) {
                Dialog(
                    onDismissRequest = {
                        coroutine.launch {
                            loginViewModel.effects.send(LoginEffect.Loading(false))
                        }
                    },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        is LoginEffect.Navigate -> navigate((effects as LoginEffect.Navigate).route)
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
                formValue = loginViewModel.viewState.email,
                onChangeValue = { loginViewModel.sendEvent { LoginEvent.OnEmailChange(it) } },
                hasError = loginViewModel.viewState.emailError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = loginViewModel.viewState.password,
                onChangeValue = { loginViewModel.sendEvent { LoginEvent.OnPasswordChange(it) } },
                error = loginViewModel.viewState.passwordError
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = { loginViewModel.sendEvent { LoginEvent.DoLogin } },
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
            onClick = { navigate(Screens.SignUp.route) }
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
    LoginContent(navigate = {})
}