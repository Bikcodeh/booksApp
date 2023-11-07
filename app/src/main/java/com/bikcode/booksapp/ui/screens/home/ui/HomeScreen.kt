package com.bikcode.booksapp.ui.screens.home.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.bikcode.booksapp.navigation.SetupBottomNavGraphAdmin
import com.bikcode.booksapp.ui.components.BottomBarAdmin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    mainNavController: NavHostController,
    bottomNavController: NavHostController,
    onLogOut: () -> Unit
) {
    Scaffold(bottomBar = { BottomBarAdmin(bottomNavController) }) {
        SetupBottomNavGraphAdmin(
            mainNavController = mainNavController,
            bottomNavController = bottomNavController,
            onLogOut = onLogOut
        )
    }
}