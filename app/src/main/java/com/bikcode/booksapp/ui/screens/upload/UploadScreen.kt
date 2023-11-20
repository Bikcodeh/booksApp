package com.bikcode.booksapp.ui.screens.upload

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.bikcode.booksapp.R

@Composable
fun UploadScreen(
    paddingValues: PaddingValues,
    showSnackBar: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQyCrAWdIb8-moiYuP7EdpznRLOLaKoZWJ04MLzMi1wkJrMfLKQshwXhB_ODNz3T6_aoOwQ0YccVpSbLO2K9qkpx-HTklvNm3ZR_spOINLr861_PgDXDnh6LgpptIyzR5Nv-UjlQ-5FyeLpHwOCb4NjZ8darLIomTVjHM2VvDv7YZdzO-FS6zMKEhlCQ/w1200-h630-p-k-no-nu/Android-JetpackCompose1.2-Social.png" ?: R.drawable.portrait_placeholder)
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
                .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
        )
    }
}