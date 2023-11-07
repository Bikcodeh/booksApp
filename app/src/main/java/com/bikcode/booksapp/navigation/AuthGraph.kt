package com.bikcode.booksapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bikcode.booksapp.ui.screens.login.LoginScreen
import com.bikcode.booksapp.ui.screens.signup.SignUpScreen

const val GRAPH_AUTH = "AUTH"
fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(startDestination = Screens.Login.route, route = GRAPH_AUTH) {
        composable(
            route = Screens.Login.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            LoginScreen(navigate = { navController.navigate(it) })
        }

        composable(
            route = Screens.SignUp.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                navigate = {
                    navController.navigate(it) {
                        popUpTo(Screens.SignUp.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}