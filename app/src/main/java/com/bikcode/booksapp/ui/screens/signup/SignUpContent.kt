package com.bikcode.booksapp.ui.screens.signup

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bikcode.booksapp.R
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpEffect
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpEvent
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun SignUpContent(
    onBack: () -> Unit,
    navigate: (String) -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    val effects by signUpViewModel.effects.receiveAsFlow()
        .collectAsStateWithLifecycle(initialValue = SignUpEffect.Loading(false))
    when(effects) {
        SignUpEffect.GoHome -> navigate(Screens.Home.route)
        is SignUpEffect.Loading -> {
            if ((effects as SignUpEffect.Loading).show) {
                Dialog(
                    onDismissRequest = {
                        coroutine.launch {
                            signUpViewModel.effects.send(
                                SignUpEffect.Loading(
                                    false
                                )
                            )
                        }
                    },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(White, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
                formValue = signUpViewModel.viewState.name,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnNameChange(it) } },
                hasError = signUpViewModel.viewState.nameError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldString(
                label = R.string.email,
                placeholder = R.string.email_placeholder,
                formValue = signUpViewModel.viewState.email,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnEmailChange(it) } },
                hasError = signUpViewModel.viewState.emailError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = signUpViewModel.viewState.password,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnPasswordChange(it) } },
                error = signUpViewModel.viewState.passwordError
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password_confirm,
                placeholder = R.string.password_placeholder,
                formValue = signUpViewModel.viewState.confirmPassword,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnConfirmPasswordChange(it) } },
                error = signUpViewModel.viewState.confirmPasswordError
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.height(50.dp),
                onClick = { signUpViewModel.sendEvent { SignUpEvent.DoSignUp } },
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