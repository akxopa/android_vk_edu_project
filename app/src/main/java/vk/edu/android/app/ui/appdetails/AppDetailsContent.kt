package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vk.edu.android.app.model.AppDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsContent(
    app: AppDetails,
    onBack: () -> Unit,
    onAppClick: (String) -> Unit,
    onInstallClick: () -> Unit,
    similarApps: List<AppDetails>
) {
    Scaffold(
        topBar = {
            AppDetailsToolbar(title = app.name, onBack = onBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 0.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            AppDetailsHeader(app = app)

            // Кнопка установки
            Spacer(Modifier.height(16.dp))
            InstallButton(onClick = onInstallClick)

            // Описание подкат
            Spacer(Modifier.height(32.dp))
            var isDescriptionExpanded by remember { mutableStateOf(false) }
            AppDescription(
                description = app.description,
                isExpanded = isDescriptionExpanded,
                onToggle = { isDescriptionExpanded = !isDescriptionExpanded })

            // Скриншоты
            Spacer(Modifier.height(8.dp))
            if (app.screenshotResList.isNotEmpty()) {
                ScreenshotsList(screenshots = app.screenshotResList, appName = app.name)
            }
            // Похожие приложения
            Spacer(Modifier.height(16.dp))
            SimilarAppsSection(similarApps, onAppClick = onAppClick, onBack = onBack)
        }
    }

}

