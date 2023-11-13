package com.bikcode.booksapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.bikcode.booksapp.ui.theme.backgroundColor
import com.bikcode.booksapp.ui.theme.cardColor
import com.bikcode.booksapp.ui.theme.formTextColorSecondary
import com.bikcode.booksapp.ui.theme.iconColor


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
            containerColor = MaterialTheme.colorScheme.cardColor
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
                color = MaterialTheme.colorScheme.formTextColorSecondary
            )
            Icon(
                modifier = Modifier.padding(end = 16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.iconColor
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

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CardRowActionPreviewDark() {
    CardRowAction(title = "Personal") {

    }
}