package com.bikcode.booksapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bikcode.booksapp.navigation.GRAPH_AUTH
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.navigation.SetupNavGraph
import com.bikcode.booksapp.ui.RootActivity
import com.bikcode.booksapp.ui.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fromLogout = intent.getBooleanExtra("from_logout", false)
        val startDestination = if (fromLogout) GRAPH_AUTH else Screens.Splash.route
        setContent {
            BooksAppTheme {
                navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(
                        navController = navHostController,
                        startDestination = startDestination,
                        goHome = {
                            startActivity(Intent(this, RootActivity::class.java))
                            finishAffinity()
                        }
                    )
                }
            }
        }
    }
}
