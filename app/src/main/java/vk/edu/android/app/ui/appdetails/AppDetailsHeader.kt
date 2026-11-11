package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.Category
import vk.edu.android.app.model.toDisplayName
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme

@Composable
fun AppDetailsHeader(
    app: AppDetails,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = app.name,
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = app.category.toDisplayName(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = app.developer,
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoColumn(
                    value = "+${app.ageRating}",
                    label = "Возраст"
                )
                InfoColumn(
                    value = "${app.size} МБ",
                    label = "Размер"
                )

            }
        }
    }
}

@Composable
private fun InfoColumn(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(value, style = MaterialTheme.typography.bodySmall)
        Text(label, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailsHeaderPreview() {
    val mockApp1 = AppDetails(
        id = "preview_id_1",
        name = "Тестовое Первое",
        developer = "VK",
        category = Category.GAME,
        ageRating = 12,
        size = 42.5f,
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg",
        screenshotUrlList = listOf(
            "https://fastly.picsum.photos/id/12/200/200.jpg",
            "https://fastly.picsum.photos/id/13/200/200.jpg",
            "https://fastly.picsum.photos/id/14/200/200.jpg"
        ),
        description = "Это пример длинного описания приложения, которое будет красиво прокручиваться на экране вместе со скриншотами."
    )

    VkEduAndroidAppTheme{
        AppDetailsHeader(app = mockApp1)
    }

}

