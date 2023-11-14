package com.bikcode.booksapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bikcode.booksapp.ui.screens.account.ui.AccountScreen
import com.bikcode.booksapp.ui.screens.category.CategoryScreen
import com.bikcode.booksapp.ui.screens.account.ui.password.ChangePasswordScreen
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
        startDestination = BottomBarRoutesAdmin.DASHBOARD.route
    ) {
        composable(
            route = BottomBarRoutesAdmin.DASHBOARD.route,
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
            DashboardScreen(paddingValues, showSnackBar)
        }
        composable(
            route = BottomBarRoutesAdmin.ACCOUNT.route,
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
            route = BottomBarRoutesAdmin.UPLOAD.route,
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
            route = BottomBarRoutesAdmin.CATEGORY.route,
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
            ChangePasswordScreen(onBack = { navController.popBackStack() }, showSnackBar)
        }
    }
}