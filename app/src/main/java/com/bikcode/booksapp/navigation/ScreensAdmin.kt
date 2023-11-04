package com.bikcode.booksapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bikcode.booksapp.R

sealed class ScreensAdmin(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    object Dashboard : ScreensAdmin(
        route = "dashboard_screen",
        title = R.string.dashboard_description,
        icon = R.drawable.ic_dashboard
    )

    object Account : ScreensAdmin(
        route = "account_screen",
        title = R.string.account_description,
        icon = R.drawable.ic_account
    )
}
