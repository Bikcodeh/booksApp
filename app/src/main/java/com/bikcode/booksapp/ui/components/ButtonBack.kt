package com.bikcode.booksapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .size(42.dp)
        .clip(CircleShape)
        .background(Color.White)
        .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.5f)
        )
    }
}

@Preview
@Composable
private fun ButtonBackPreview() {
    ButtonBack(onClick = {})
}