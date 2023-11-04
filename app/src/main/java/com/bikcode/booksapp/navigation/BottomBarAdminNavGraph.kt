package com.bikcode.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.ui.screens.account.AccountScreen
import com.bikcode.booksapp.ui.screens.dashboard.DashboardScreen

@Composable
fun SetupBottomNavGraphAdmin(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ScreensAdmin.Dashboard.route) {
        composable(route = ScreensAdmin.Dashboard.route) {
            DashboardScreen()
        }
        composable(route = ScreensAdmin.Account.route) {
            AccountScreen()
        }
    }
}