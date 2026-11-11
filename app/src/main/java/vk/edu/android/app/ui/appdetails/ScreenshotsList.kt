package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ScreenshotsList(
    screenshots: List<String>,
    appName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.padding(vertical = 16.dp)
    ){
        Text(
            text = "Скриншоты",
            style = MaterialTheme.typography.titleMedium,
        )
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = screenshots,
            key = { resId -> resId }
        ) { screenshotUrl ->
            AsyncImage(
                model = screenshotUrl,
                contentDescription = "Скриншот приложения $appName",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(128.dp)
                    .height(390.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}