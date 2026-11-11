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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import vk.edu.android.app.model.AppListItem
import vk.edu.android.app.model.Category
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme

@Composable
fun AppListScreen(
    viewModel: AppListViewModel,
    onAppClick: (String) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val state by viewModel.screenStateAppList.collectAsStateWithLifecycle()
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
                apps = currentState.apps,
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
    VkEduAndroidAppTheme {
        val mockApp1 = AppListItem(
            id = "preview_id_1",
            name = "Тестовое Первое",
            shortDescription = "Короткое описание первого приложения",
            category = Category.GAME,
            iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg"
        )

        val mockApp2 = AppListItem(
            id = "preview_id_2",
            name = "Тестовое Второе",
            shortDescription = "Короткое описание второго приложения",
            category = Category.ENTERTAINMENT,
            iconUrl = "https://fastly.picsum.photos/id/238/200/200.jpg"
        )

        AppListContent(
            apps = listOf(mockApp1, mockApp2),
            snackbarHostState = remember { SnackbarHostState() },
            onToolbarClick = {},
            onAppClick = {}
        )
    }
}