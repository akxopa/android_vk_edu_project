package vk.edu.android.app.ui.appdetails

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.collectLatest
import vk.edu.android.app.R
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.Category
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme

@Composable
fun AppListScreen(
    viewModel: AppListViewModel,
    onAppClick: (String) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AppListUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    AppListContent(
        apps = viewModel.apps,
        snackbarHostState = snackbarHostState,
        onToolbarClick = { viewModel.onToolbarClick() },
        onAppClick = onAppClick
    )
}

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    VkEduAndroidAppTheme() {

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

        AppListContent(
            apps = listOf(mockApp1, mockApp2),
            snackbarHostState = remember { SnackbarHostState() },
            onToolbarClick = {},
            onAppClick = {}
        )
    }
}