package com.bikcode.booksapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.ui.screens.account.AccountScreen
import com.bikcode.booksapp.ui.screens.category.CategoryScreen
import com.bikcode.booksapp.ui.screens.changepassword.ChangePasswordScreen
import com.bikcode.booksapp.ui.screens.dashboard.DashboardScreen
import com.bikcode.booksapp.ui.screens.upload.UploadScreen

@Composable
fun SetupBottomNavGraphAdmin(
    navController: NavHostController,
    onLogOut: () -> Unit,
    paddingValues: PaddingValues,
    showSnackBar: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreensAdmin.Dashboard.route
    ) {
        composable(
            route = ScreensAdmin.Dashboard.route,
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
            DashboardScreen(onLogOut, paddingValues, showSnackBar)
        }
        composable(
            route = ScreensAdmin.Account.route,
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
            AccountScreen(
                paddingValues = paddingValues,
                onLogOut = onLogOut,
                showSnackBar = showSnackBar,
                navigate = { navController.navigate(it) }
            )
        }

        composable(
            route = ScreensAdmin.Upload.route,
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
            UploadScreen(paddingValues, showSnackBar)
        }

        composable(
            route = ScreensAdmin.Category.route,
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
            CategoryScreen(paddingValues, showSnackBar)
        }
        composable(route = Screens.ChangePassword.route) {
            ChangePasswordScreen(onBack = { navController.popBackStack() })
        }
    }
}