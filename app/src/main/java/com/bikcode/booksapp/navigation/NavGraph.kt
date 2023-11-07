package com.bikcode.booksapp.navigation

import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    goHome: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = "ROOT"
    ) {
        composable(
            route = Screens.Splash.route,
            exitTransition = {
                fadeOut()
            }
        ) {
            SplashScreen(
                navigate = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        authGraph(navController, goHome)
    }
}