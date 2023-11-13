package com.bikcode.booksapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bikcode.booksapp.R

enum class BottomBarRoutesAdmin(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    DASHBOARD(
        route = "dashboard_screen",
        title = R.string.dashboard_description,
        icon = R.drawable.ic_dashboard
    ),

     ACCOUNT(
        route = "account_screen",
        title = R.string.account_description,
        icon = R.drawable.ic_account
    ),
     UPLOAD(
        route = "upload_screen",
        title = R.string.upload_description,
        icon = R.drawable.ic_upload
    ),
     CATEGORY(
        route = "category_screen",
        title = R.string.category_description,
        icon = R.drawable.ic_category
    )
}
