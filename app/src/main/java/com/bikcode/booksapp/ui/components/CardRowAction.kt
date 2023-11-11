package com.bikcode.booksapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.theme.CoolGrey


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRowAction(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CoolGrey
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                color = Color.Black.copy(0.5f)
            )
            Icon(
                modifier = Modifier.padding(end = 16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview
@Composable
private fun CardRowActionPreview() {
    CardRowAction(title = "Personal") {

    }
}