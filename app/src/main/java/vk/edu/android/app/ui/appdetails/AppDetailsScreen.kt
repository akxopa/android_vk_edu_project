package vk.edu.android.app.ui.appdetails

import vk.edu.android.app.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme
import vk.edu.android.app.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    onAppClick: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: AppDetailsViewModel
) {
    val app by viewModel.selectedApp.collectAsState()
    val similarApps by viewModel.similarApp.collectAsState()

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
        AppDetailsContent(
            app = app!!,
            similarApps = similarApps, // Передаем список сюда
            onAppClick = onAppClick,
            onBack = onBack
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AppDetailsScreenPreview() {
    val mockApp1 = AppDetails(
        id = "preview_id_1",
        name = "Тестовое Первое",
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

    val mockApp2 = AppDetails(
        id = "preview_id_2",
        name = "Тестовое Второе",
        developer = "VK",
        category = Category.GAME,
        ageRating = 12,
        size = 42.5f,
        iconRes = R.drawable.ic_my_music,
        screenshotResList = listOf(
            R.drawable.ic_weather_sc1,
            R.drawable.ic_weather_sc2,
            R.drawable.ic_weather_sc3
        ),
        shortDescription = "Короткое описание",
        description = "Это пример длинного описания приложения, которое будет красиво прокручиваться на экране вместе со скриншотами."
    )

    VkEduAndroidAppTheme {
        AppDetailsContent(similarApps = listOf(mockApp1, mockApp2), onBack = {}, onAppClick = {}, app = mockApp1)
    }
}