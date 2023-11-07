package com.bikcode.booksapp.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bikcode.booksapp.ui.components.CategoryBook

@Composable
fun DashboardScreen(
    onLogOut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onLogOut() }) {
            Text(text = "Logout")
        }

        CategoryBook(onEdit = {}, onDelete = {}, textValue = "Action")
    }
}