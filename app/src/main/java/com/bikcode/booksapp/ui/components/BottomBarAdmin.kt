package com.bikcode.booksapp.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bikcode.booksapp.navigation.BottomBarRoutesAdmin


@Composable
fun BottomBarAdmin(navController: NavHostController) {
    val screens = listOf(
        BottomBarRoutesAdmin.DASHBOARD,
        BottomBarRoutesAdmin.UPLOAD,
        BottomBarRoutesAdmin.CATEGORY,
        BottomBarRoutesAdmin.ACCOUNT
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar() {
        screens.forEach { item ->
            AddItem(
                menuItem = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    menuItem: BottomBarRoutesAdmin,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == menuItem.route
        } == true,
        onClick = {
            navController.navigate(menuItem.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = menuItem.icon),
                contentDescription = null
            )
        },
        label = { Text(text = stringResource(id = menuItem.title)) }
    )
}