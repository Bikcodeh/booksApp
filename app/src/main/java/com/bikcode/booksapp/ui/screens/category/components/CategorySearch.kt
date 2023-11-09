package com.bikcode.booksapp.ui.screens.category.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bikcode.booksapp.R
import com.bikcode.booksapp.ui.theme.iconTintColor

@Composable
fun CategorySearch(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    onClearFilter: () -> Unit
) {
    var showArrowBack by remember { mutableStateOf(false) }
    var showClearText by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current
    var text by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Medium,
        shadowElevation = 4.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (leading, field, trailing) = createRefs()
            IconButton(
                onClick = {
                    localFocusManager.clearFocus()
                    if (text.isNotEmpty()) text = ""
                    onClearFilter()
                },
                modifier = Modifier
                    .constrainAs(leading) {
                        start.linkTo(parent.start)
                        linkTo(parent.top, parent.bottom)
                    },
                enabled = showArrowBack || text.isNotEmpty(),
                colors = IconButtonDefaults.iconButtonColors(
                    disabledContentColor = MaterialTheme.colorScheme.iconTintColor
                )
            ) {
                Icon(
                    painter = painterResource(id = if (showArrowBack || text.isNotEmpty()) R.drawable.ic_arrow_back else R.drawable.ic_search),
                    contentDescription = null
                )
            }
            BasicTextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusEvent {
                        showArrowBack = it.isFocused
                    }
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .constrainAs(field) {
                        start.linkTo(leading.end)
                        linkTo(parent.top, parent.bottom)
                        end.linkTo(trailing.start, goneMargin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                value = text,
                onValueChange = {
                    text = it
                    onTextChange(it)
                    if (text.isNotEmpty()) {
                        showClearText = true
                    } else {
                        onClearFilter()
                    }
                },
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        showClearText = false
                        Text(
                            text = stringResource(id = R.string.search_placeholder),
                            color = Color.Gray
                        )
                    } else {
                        showClearText = true
                    }
                    innerTextField.invoke()
                },
                maxLines = 1,
                singleLine = true
            )
            if (showClearText) {
                IconButton(
                    onClick = {
                        text = ""
                        showClearText = false
                        onClearFilter()
                    },
                    modifier = Modifier
                        .constrainAs(trailing) {
                            end.linkTo(parent.end)
                            linkTo(parent.top, parent.bottom)
                        },
                    enabled = showArrowBack || text.isNotEmpty(),
                    colors = IconButtonDefaults.iconButtonColors(
                        disabledContentColor = MaterialTheme.colorScheme.iconTintColor
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategorySearchPreview() {
    CategorySearch(onClearFilter = {}, onTextChange = {})
}