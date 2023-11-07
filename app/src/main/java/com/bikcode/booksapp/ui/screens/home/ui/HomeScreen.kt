package com.bikcode.booksapp.ui.screens.home.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.view.WindowManager
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.bikcode.booksapp.navigation.SetupBottomNavGraphAdmin
import com.bikcode.booksapp.ui.components.BottomBarAdmin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onLogOut: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBarAdmin(navController) }) {
        SetupBottomNavGraphAdmin(navController, onLogOut)
    }
}