package vk.edu.android.app.ui.applist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    val state by viewModel.screenStateAppList.collectAsState()
    when (val currentState = state) {
        is AppListScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AppListScreenState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentState.message,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { viewModel.loadApps() }
                ) {
                    Text("Повторить попытку")
                }
            }
        }

        is AppListScreenState.Success -> {
            AppListContent(
                apps = viewModel.apps,
                snackbarHostState = snackbarHostState,
                onToolbarClick = { viewModel.onToolbarClick() },
                onAppClick = onAppClick
            )
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AppListUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }


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