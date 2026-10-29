package vk.edu.android.app.ui.appdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstallButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = { /* добавим позже */ },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Установить")
    }
}