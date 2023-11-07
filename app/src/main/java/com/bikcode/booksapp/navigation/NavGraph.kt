package com.bikcode.booksapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.ui.screens.home.ui.HomeScreen
import com.bikcode.booksapp.ui.screens.splash.SplashScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
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
        composable(
            route = Screens.Home.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            }
        ) {
            HomeScreen(
                onLogOut = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(GRAPH_AUTH) {
                        popUpTo(GRAPH_AUTH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        authGraph(navController)
    }
}