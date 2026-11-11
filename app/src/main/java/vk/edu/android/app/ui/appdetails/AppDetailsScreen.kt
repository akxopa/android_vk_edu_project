package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import vk.edu.android.app.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme
import vk.edu.android.app.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    onAppClick: (String) -> Unit,
    onInstallClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: AppDetailsViewModel
) {
    val state by viewModel.screenState.collectAsState()

    when (val currentState = state) {
        is AppDetailsScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AppDetailsScreenState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentState.message,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(onClick = onBack) {
                    Text("Назад")
                }
            }
        }

        is AppDetailsScreenState.Success -> {
            AppDetailsContent(
                app = currentState.app,
                onBack = onBack,
                onAppClick = onAppClick,
                onInstallClick = onInstallClick,
                similarApps = currentState.similarApps
            )
        }
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
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg",
        screenshotUrlList = listOf(
            "https://fastly.picsum.photos/id/12/200/200.jpg",
            "https://fastly.picsum.photos/id/13/200/200.jpg",
            "https://fastly.picsum.photos/id/14/200/200.jpg"
        ),
        description = "Это пример длинного описания приложения, которое будет красиво прокручиваться на экране вместе со скриншотами."
    )

    val mockApp2 = AppDetails(
        id = "preview_id_2",
        name = "Тестовое Второе",
        developer = "VK",
        category = Category.GAME,
        ageRating = 12,
        size = 42.5f,
        iconUrl = "https://fastly.picsum.photos/id/239/200/201.jpg",
        screenshotUrlList = listOf(
            "https://fastly.picsum.photos/id/12/200/201.jpg",
            "https://fastly.picsum.photos/id/13/200/201.jpg",
            "https://fastly.picsum.photos/id/14/200/201.jpg"
        ),
        description = "Это пример длинного описания приложения, которое будет красиво прокручиваться на экране вместе со скриншотами."
    )

    val mockApp3 = AppListItem(
        id = "preview_id_1",
        name = "Тестовое Первое",
        shortDescription = "Короткое описание первого приложения",
        category = Category.GAME,
        iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg"
    )

    val mockApp4 = AppListItem(
        id = "preview_id_2",
        name = "Тестовое Второе",
        shortDescription = "Короткое описание второго приложения",
        category = Category.ENTERTAINMENT,
        iconUrl = "https://fastly.picsum.photos/id/238/200/200.jpg"
    )

    VkEduAndroidAppTheme {
        AppDetailsContent(
            similarApps = listOf(mockApp3, mockApp4),
            onBack = {},
            onAppClick = {},
            app = mockApp1,
            onInstallClick = {})
    }
}