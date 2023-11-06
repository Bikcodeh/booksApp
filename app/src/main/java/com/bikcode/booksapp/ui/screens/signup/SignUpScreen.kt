package com.bikcode.booksapp.ui.screens.signup

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignUpScreen(onBack: () -> Unit, navigate: (String) -> Unit) {
    SignUpContent(onBack, navigate = navigate)
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
fun SignUpScreenPreviewDark() {
    SignUpScreen(onBack = {}, navigate = {})
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,dpi=160"
)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(onBack = {}, navigate = {})
}