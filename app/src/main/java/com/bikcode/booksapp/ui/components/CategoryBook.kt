package com.bikcode.booksapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.theme.cardColor
import com.bikcode.booksapp.ui.theme.formTextColor


@Composable
fun CategoryBook(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    textValue: String
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Medium,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.cardColor
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val (text, edit, delete) = createRefs()
            Text(
                text = textValue,
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.formTextColor
            )
            IconButton(
                onClick = onEdit,
                modifier = Modifier.constrainAs(edit) {
                    end.linkTo(delete.start)
                    top.linkTo(parent.top)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
private fun CategoryBookPreview() {
    CategoryBook(onDelete = {}, onEdit = {}, textValue = "Action")
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CategoryBookPreviewDark() {
    CategoryBook(onDelete = {}, onEdit = {}, textValue = "Action")
}