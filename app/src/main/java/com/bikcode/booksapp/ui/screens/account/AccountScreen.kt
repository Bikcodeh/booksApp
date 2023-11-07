package com.bikcode.booksapp.ui.screens.account

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen(
    goSettings: () -> Unit
) {
    Text(text = "ACCOUNT", modifier = Modifier.clickable {
        goSettings()
    })
}