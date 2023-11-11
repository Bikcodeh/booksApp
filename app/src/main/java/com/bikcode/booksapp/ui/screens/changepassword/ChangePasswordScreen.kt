package com.bikcode.booksapp.ui.screens.changepassword

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bikcode.booksapp.ui.components.ButtonBack
import com.bikcode.booksapp.ui.utils.extension.clearFocusOnClickOutside

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val requestFocus = LocalFocusManager.current
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clearFocusOnClickOutside(requestFocus)
    ) {
        val (back, form, button) = createRefs()
        ButtonBack(
            modifier = Modifier.constrainAs(back) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }
        ) {
            onBack()
        }
    }
}

@Preview
@Composable
private fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(onBack = {})
}