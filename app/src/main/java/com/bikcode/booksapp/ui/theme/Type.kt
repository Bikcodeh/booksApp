package com.bikcode.booksapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.bikcode.booksapp.R

val fonts = FontFamily(
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_regular, weight = FontWeight.Normal)
)
private val originalTypography = Typography()

val BooksAppTypography = Typography(
    titleLarge = originalTypography.titleLarge.copy(fontFamily = fonts),
    titleMedium = originalTypography.titleMedium.copy(fontFamily = fonts),
    titleSmall = originalTypography.titleSmall.copy(fontFamily = fonts),
    bodyLarge = originalTypography.bodyLarge.copy(fontFamily = fonts),
    bodyMedium = originalTypography.bodyMedium.copy(fontFamily = fonts),
    bodySmall = originalTypography.bodySmall.copy(fontFamily = fonts),
    headlineLarge = originalTypography.headlineLarge.copy(fontFamily = fonts),
    headlineMedium = originalTypography.headlineMedium.copy(fontFamily = fonts),
    headlineSmall = originalTypography.headlineSmall.copy(fontFamily = fonts),
    labelLarge = originalTypography.labelLarge.copy(fontFamily = fonts),
    labelMedium = originalTypography.labelMedium.copy(fontFamily = fonts),
    labelSmall = originalTypography.labelSmall.copy(fontFamily = fonts),
    displayLarge = originalTypography.displayLarge.copy(fontFamily = fonts),
    displayMedium = originalTypography.displayMedium.copy(fontFamily = fonts),
    displaySmall = originalTypography.displaySmall.copy(fontFamily = fonts),
)