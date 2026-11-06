package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vk.edu.android.app.data.AppDetailsRepository
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository
) : ViewModel() {
    private val allApps = repository.apps

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