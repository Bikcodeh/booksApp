package com.bikcode.booksapp.ui.screens.home.ui

import android.app.Activity
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.eventbus.EventBusViewModel
import com.bikcode.booksapp.core.eventbus.events.CategoryFab
import com.bikcode.booksapp.core.eventbus.events.HideBottomBarEvent
import com.bikcode.booksapp.core.eventbus.events.InitialState
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.navigation.ScreensAdmin
import com.bikcode.booksapp.navigation.SetupBottomNavGraphAdmin
import com.bikcode.booksapp.ui.components.BottomBarAdmin
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onLogOut: () -> Unit,
    eventBusViewModel: EventBusViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val coroutines = rememberCoroutineScope()
    var showBottomBar by remember { mutableStateOf(true) }
    var showFab by remember { mutableStateOf(false) }
    var fabClick by remember { mutableStateOf<() -> Unit>({}) }
    val currentRoute by navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val events by eventBusViewModel.getEvents()
        .collectAsStateWithLifecycle(initialValue = InitialState)
    LaunchedEffect(key1 = events) {
        when (val busEvent = events) {
            is CategoryFab -> {
                showFab = busEvent.show
                fabClick = busEvent.onClick
            }

            InitialState -> {}
            is HideBottomBarEvent -> { showBottomBar = busEvent.show }
        }
    }
    LaunchedEffect(key1 = currentRoute?.destination?.route) {
        showBottomBar = when (currentRoute?.destination?.route) {
            Screens.ChangePassword.route -> false
            else -> true
        }
        val decor = when (currentRoute?.destination?.route) {
            ScreensAdmin.Category.route -> false
            else -> true
        }
        if (decor)
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        else
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                exit = slideOutVertically(targetOffsetY = { it }),
                enter = slideInVertically(initialOffsetY = { it })
            ) {
                BottomBarAdmin(navController)
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            AnimatedVisibility(visible = showFab, exit = scaleOut(), enter = scaleIn()) {
                FloatingActionButton(onClick = { fabClick.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        SetupBottomNavGraphAdmin(
            navController,
            onLogOut,
            it,
            showSnackBar = {
                coroutines.launch { snackBarHostState.showSnackbar(it) }
            }
        )
    }
}