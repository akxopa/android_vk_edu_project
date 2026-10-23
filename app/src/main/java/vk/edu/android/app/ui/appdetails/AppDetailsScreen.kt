package vk.edu.android.app.ui.appdetails

import vk.edu.android.app.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme
import vk.edu.android.app.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    appId: String,
    onAppClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val app = AppDetailsRepository.apps.find { it.id == appId }

    if (app == null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Приложение не найдено")
            Button(onClick = onBack) {
                Text("Назад")
            }
        }
        return
    } else {
        AppDetailsContent(app = app, onAppClick = onAppClick, onBack = onBack)
    }

}

@Preview(showBackground = true)
@Composable
fun AppDetailsScreenPreview() {
    val mockApp = AppDetails(
        id = "preview_id",
        name = "Тестовое Приложение",
        developer = "VK",
        category = Category.GAME,
        ageRating = 12,
        size = 42.5f,
        iconRes = R.drawable.ic_ready_to_summer,
        screenshotResList = listOf(
            R.drawable.ic_heroes_sc1,
            R.drawable.ic_heroes_sc2,
            R.drawable.ic_heroes_sc3
        ),
        shortDescription = "Короткое описание",
        description = "Это пример длинного описания приложения, которое будет красиво прокручиваться на экране вместе со скриншотами."
    )

    VkEduAndroidAppTheme {
        AppDetailsContent(app = mockApp, onAppClick = {}, onBack = {})
    }
}