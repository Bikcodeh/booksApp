package com.bikcode.booksapp.ui.screens.account.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.bikcode.booksapp.domain.model.User
import com.bikcode.booksapp.ui.components.CardRowAction
import com.bikcode.booksapp.ui.components.Loading
import com.bikcode.booksapp.ui.screens.account.ui.components.ProfilePicture
import com.bikcode.booksapp.ui.screens.account.ui.viewmodel.AccountUiState
import com.bikcode.booksapp.ui.utils.extension.clearFocusOnClickOutside

@Composable
fun AccountContent(
    paddingValues: PaddingValues,
    uiState: AccountUiState,
    onLogOut: () -> Unit,
    onChangePasswordClick: (String) -> Unit,
    onPersonalInfoClick: (User) -> Unit,
    showSnackBar: (String) -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    if (uiState.isLoading) {
        Loading()
    } else {
        AnimatedVisibility(
            visible = true, exit = scaleOut(), enter = scaleIn(
                animationSpec = tween(800)
            )
        ) {
            uiState.user?.let { safeUser ->
                uiState.error?.let {
                    showSnackBar(it.toString())
                }
                ConstraintLayout(
                    modifier = Modifier
                        .clearFocusOnClickOutside(localFocusManager)
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    val (name, background, profilePicture, form) = createRefs()
                    Image(
                        modifier = Modifier
                            .height(250.dp)
                            .constrainAs(background) {
                                linkTo(parent.start, parent.end)
                                linkTo(parent.top, parent.bottom, bias = 0f)
                                width = Dimension.fillToConstraints
                                height = Dimension.preferredWrapContent
                            },
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = R.drawable.wave),
                        contentDescription = null
                    )
                    Text(
                        text = safeUser.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.constrainAs(name) {
                            linkTo(parent.start, parent.end, startMargin = 16.dp, endMargin = 16.dp)
                            top.linkTo(profilePicture.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                    )
                    ProfilePicture(
                        modifier = Modifier.constrainAs(profilePicture) {
                            linkTo(parent.start, parent.end)
                            top.linkTo(background.top, 124.dp)
                            bottom.linkTo(background.bottom)
                        }
                    )
                    Column(
                        modifier = Modifier
                            .constrainAs(form) {
                                top.linkTo(name.bottom, 16.dp)
                                linkTo(parent.start, parent.end)
                            }
                            .padding(16.dp)
                    ) {
                        CardRowAction(title = stringResource(id = R.string.personal_info)) {
                            onPersonalInfoClick(safeUser)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CardRowAction(title = stringResource(id = R.string.change_password)) {
                            onChangePasswordClick(safeUser.password)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            shape = ShapeDefaults.Medium,
                            onClick = { onLogOut() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.logout).uppercase(),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountContentPreview() {
    AccountContent(
        onPersonalInfoClick = {},
        paddingValues = PaddingValues(),
        onLogOut = { },
        uiState = AccountUiState(
            name = "John Doe", user = User(
                uid = "tritani",
                name = "Mai Harrison",
                email = "romeo.dyer@example.com",
                password = "conceptam",
                image = "mattis"
            )
        ),
        onChangePasswordClick = {},
        showSnackBar = {}
    )
}