package com.bikcode.booksapp.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bikcode.booksapp.ui.theme.formTextColor

@Composable
fun CardBook(
    modifier: Modifier = Modifier,
    titleBook: String,
    authorBook: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://marketplace.canva.com/EADzX-9e-Qk/1/0/1003w/canva-marr%C3%B3n-steampunk-sombrerero-creativo-portada-de-libro-electr%C3%B3nico-J4oYUx8TT4s.jpg")
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = titleBook,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = authorBook,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.formTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun CardBookPreview() {
    CardBook(
        titleBook = "Cien años de soledad",
        authorBook = "Gabriel García Marquéz",
        imageUrl = "",
        onClick = {}
    )
}