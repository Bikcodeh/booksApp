package com.bikcode.booksapp.ui.screens.home.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bikcode.booksapp.navigation.SetupBottomNavGraphAdmin
import com.bikcode.booksapp.ui.components.BottomBarAdmin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onLogOut: () -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBarAdmin(navController) }) {
        SetupBottomNavGraphAdmin(navController, onLogOut)
    }
}