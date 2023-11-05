package com.bikcode.booksapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Gray = Color(0xFF2D2D2D)
val GrayLight = Color(0xFF414141)
val CoolGrey = Color(0xFFF5F5F5)
val BlackGray = Color(0xFF191C20)

val ColorScheme.formTextColor
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.Black.copy(alpha = 0.6f) else CoolGrey

val ColorScheme.formFieldColorUnfocused
    @Composable
    get() = if (!isSystemInDarkTheme()) CoolGrey else Gray

val ColorScheme.formFieldColorFocused
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else GrayLight