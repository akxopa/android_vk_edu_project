package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vk.edu.android.app.model.InstallState

@Composable
fun InstallButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    installState: InstallState
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            enabled = installState !is InstallState.Installed,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = when (installState) {
                    InstallState.NotInstalled -> "Установить"
                    is InstallState.Installing -> "Отменить"
                    InstallState.Installed -> "Установлено"
                }
            )
        }

        if (installState is InstallState.Installing) {
            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { installState.progress / 100f },
                modifier = Modifier.fillMaxWidth()
            )

            Text("${installState.progress}%")
        }
    }
}