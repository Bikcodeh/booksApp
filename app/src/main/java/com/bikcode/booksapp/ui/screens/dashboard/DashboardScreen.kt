package com.bikcode.booksapp.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bikcode.booksapp.ui.components.CategoryBook
import com.bikcode.booksapp.ui.screens.home.components.CardBook
import okhttp3.internal.immutableListOf
import okhttp3.internal.toImmutableList

@Stable
data class Example(
    val title: String,
    val author: String,
    val image: String
)

@Composable
fun DashboardScreen(
    paddingValues: PaddingValues,
    showSnackBar: (String) -> Unit,
    onClick: () -> Unit
) {
    val state = rememberLazyGridState()
    val list = remember { mutableListOf<Example>().apply {
        add(Example(title = "The Great Bank Robbery", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery1", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery2", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery3", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery4", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery5", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery6", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery7", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery8", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery9", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery10", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery11", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery12", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery13", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery14", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery15", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery16", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery17", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery18", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery19", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery20", author = "Gabriel Garcia Marquez", image = ""))
        add(Example(title = "The Great Bank Robbery21", author = "Gabriel Garcia Marquez", image = ""))
    } }
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(list, key = { it.title }) {
            CardBook(
                modifier = Modifier.padding(4.dp),
                titleBook = "${it.title} $it",
                authorBook = "${it.author} $it",
                imageUrl = "",
                onClick = onClick
            )
        }
    }
}