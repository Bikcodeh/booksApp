package com.bikcode.booksapp.ui.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.components.FormFieldString
import com.bikcode.booksapp.ui.screens.account.components.ProfilePicture
import com.bikcode.booksapp.ui.theme.CoolGrey

@Composable
fun AccountScreen(
    paddingValues: PaddingValues,
    onBack: () -> Unit,
    onLogOut: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (back, background, profilePicture, form, logout) = createRefs()
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
        Box(modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onBack() }
            .constrainAs(back) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.5f)
            )
        }
        Box(modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onLogOut() }
            .constrainAs(logout) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top, 16.dp)
            },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.5f)
            )
        }
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
                    top.linkTo(profilePicture.bottom, 40.dp)
                    linkTo(parent.start, parent.end)
                }
                .padding(16.dp)
        ) {
            FormFieldString(
                label = R.string.name,
                placeholder = R.string.name_placeholder,
                formValue = "",
                onChangeValue = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            FormFieldString(
                label = R.string.password,
                placeholder = R.string.password_placeholder,
                formValue = "",
                onChangeValue = {}
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.save_option))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountScreenPreview() {
    AccountScreen(
        paddingValues = PaddingValues(),
        onBack = {},
        onLogOut = {}
    )
}