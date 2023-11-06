package com.bikcode.booksapp.ui.screens.signup

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.components.FormFieldStringPassword
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpEvent
import com.bikcode.booksapp.ui.screens.signup.viewmodel.SignUpViewModel

@Composable
fun SignUpContent(
    onBack: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
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
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnNameChange(it) } }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldString(
                label = R.string.email,
                placeholder = R.string.email_placeholder,
                formValue = signUpViewModel.viewState.email,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnEmailChange(it) } }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = signUpViewModel.viewState.password,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnPasswordChange(it) } }
            )
            Spacer(modifier = Modifier.height(12.dp))
            FormFieldStringPassword(
                label = R.string.password_confirm,
                placeholder = R.string.password_placeholder,
                formValue = signUpViewModel.viewState.confirmPassword,
                onChangeValue = { signUpViewModel.sendEvent { SignUpEvent.OnConfirmPasswordChange(it) } }
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
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}