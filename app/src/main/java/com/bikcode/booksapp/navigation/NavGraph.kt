package com.bikcode.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.screens.home.ui.HomeScreen
import com.bikcode.booksapp.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navigateToHome = {
                navController.navigate(Screens.Home.route)
            })
        }
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
    }
}