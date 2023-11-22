package com.bikcode.booksapp.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.bikcode.booksapp.R

@Composable
fun DetailScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (picture, title, author, description) = createRefs()

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQyCrAWdIb8-moiYuP7EdpznRLOLaKoZWJ04MLzMi1wkJrMfLKQshwXhB_ODNz3T6_aoOwQ0YccVpSbLO2K9qkpx-HTklvNm3ZR_spOINLr861_PgDXDnh6LgpptIyzR5Nv-UjlQ-5FyeLpHwOCb4NjZ8darLIomTVjHM2VvDv7YZdzO-FS6zMKEhlCQ/w1200-h630-p-k-no-nu/Android-JetpackCompose1.2-Social.png"
                        ?: R.drawable.portrait_placeholder
                )
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .constrainAs(picture) {
                    linkTo(parent.start, parent.end)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = "Titulo",
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(picture.bottom, 16.dp)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = "Masakio Antonio",
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(author) {
                top.linkTo(title.bottom)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "(N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejem",
            textAlign = TextAlign.Justify,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(author.bottom, 16.dp)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
            }
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailScreen()
}