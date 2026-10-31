package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vk.edu.android.app.data.AppDetailsRepository

class AppDetailsViewModel : ViewModel() {
    private val allApps = AppDetailsRepository.apps

    // Начально состояние будет Loading
    // Если данные есть, то показывай Success
    // Если данные не пришли, то Error

    // начальное состояние экрана
    private val _screenState = MutableStateFlow<AppDetailsScreenState>(AppDetailsScreenState.Loading)
    val screenState: StateFlow<AppDetailsScreenState> = _screenState.asStateFlow()

    // метод при открытии экрана
    fun loadAppDetails(appId: String) {
        _screenState.value = AppDetailsScreenState.Loading

        val currentApp = allApps.find { it.id == appId }
        if (currentApp == null) {
            _screenState.value = AppDetailsScreenState.Error("Приложение с ID $appId не найдено.")
        } else {
            val similar = allApps.filter{ it.id != appId }
            _screenState.value = AppDetailsScreenState.Success(
                app = currentApp,
                similarApps = similar
            )
        }
    }
}