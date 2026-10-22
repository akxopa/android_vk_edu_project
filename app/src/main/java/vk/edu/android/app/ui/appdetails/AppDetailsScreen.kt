package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    appId: String,
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
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(app.name) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = app.iconRes),
                    contentDescription = app.name,
                    modifier = Modifier.size(100.dp)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = app.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Категория: ${app.category}")
                    Text("Возраст: ${app.ageRating}+")
                    Text("Размер: ${app.size} МБ")
                }
            }

            Button(
                onClick = { /* Установку добавим позже */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Установить")
            }

            Text(
                text = "Описание приложения",
                style = MaterialTheme.typography.titleMedium
            )

            Text(app.description)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppDetailsScreenPreview() {
    VkEduAndroidAppTheme() {
        AppDetailsScreen(appId = "4", onBack = {})
    }
}