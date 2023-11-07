package com.bikcode.booksapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bikcode.booksapp.MainActivity
import com.bikcode.booksapp.navigation.ScreensAdmin
import com.bikcode.booksapp.navigation.SetupRootGraphAdmin
import com.bikcode.booksapp.ui.theme.BooksAppTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var bottomNavHostController: NavHostController

    private val handleOnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            println(bottomNavHostController.currentDestination?.route)
            bottomNavHostController.currentDestination?.route?.let { currentRoute ->
                if (currentRoute == ScreensAdmin.Dashboard.route) {
                    finishAffinity()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, handleOnBackPressedCallback)
        setContent {
            navHostController = rememberNavController()
            bottomNavHostController = rememberNavController()
            BooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupRootGraphAdmin(
                        navController = navHostController,
                        bottomNavController = bottomNavHostController
                    ) {
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra("from_logout", true)
                        }
                        startActivity(intent)
                        finishAffinity()
                    }
                }
            }
        }
    }
}