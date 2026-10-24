package vk.edu.android.app.ui.appdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.AppDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsContent(
    app: AppDetails,
    onBack: () -> Unit,
    onAppClick: (String) -> Unit,
    similarApps: List<AppDetails>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(app.name) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
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
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(22.dp))
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
                onClick = { /* добавим позже */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Установить")
            }

            // Описание подкат
            var isExpanded by remember { mutableStateOf(false) }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    // Делаем всю шапку кликабельной.
                    // indication = null и MutableInteractionSource убирают серый круглый инсерт при клике
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        isExpanded = !isExpanded
                    }
            ) {
                // 2. Шапка блока: текст + стрелочка
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Описание приложения",
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Меняем иконку стрелки в зависимости от состояния
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Свернуть" else "Развернуть",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // 3. Анимированный контент расхлопа
                AnimatedVisibility(visible = isExpanded) {
                    Text(
                        text = app.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            if (app.screenshotResList.isNotEmpty()) {
                Text(
                    text = "Скриншоты",
                    style = MaterialTheme.typography.titleMedium
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = app.screenshotResList,
                        key = { resId -> resId }
                    ) { screenshotRes ->
                        Image(
                            painter = painterResource(id = screenshotRes),
                            contentDescription = "Скриншот приложения ${app.name}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(220.dp)
                                .height(390.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }
            }
            SimilarAppsSection(similarApps, onAppClick = onAppClick, onBack = onBack)
        }

    }

}

