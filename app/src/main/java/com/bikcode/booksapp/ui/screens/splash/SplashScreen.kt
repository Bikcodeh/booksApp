package com.bikcode.booksapp.ui.screens.splash

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bikcode.booksapp.R
import com.bikcode.booksapp.navigation.Screens
import com.bikcode.booksapp.ui.RootActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    navigate: (String) -> Unit
) {
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        coroutine.launch {
            delay(1000)
            FirebaseAuth.getInstance().currentUser?.let {
                (context as ComponentActivity).startActivity(Intent(context, RootActivity::class.java))
                context.finishAffinity()
            } ?: run {
                navigate(Screens.Login.route)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen_bienvenida),
            contentDescription = null
        )
    }
}