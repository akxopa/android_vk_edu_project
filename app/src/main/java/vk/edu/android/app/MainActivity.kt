package vk.edu.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import vk.edu.android.app.navigation.AppNavHost
import vk.edu.android.app.ui.theme.VkEduAndroidAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEduAndroidAppTheme {
                AppNavHost()
            }
        }
    }
}
