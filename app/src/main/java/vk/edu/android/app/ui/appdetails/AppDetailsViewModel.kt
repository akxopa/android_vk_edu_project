package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vk.edu.android.app.data.AppDetailsRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository
) : ViewModel() {

    // начальное состояние экрана
    private val _screenState =
        MutableStateFlow<AppDetailsScreenState>(AppDetailsScreenState.Loading)
    val screenState: StateFlow<AppDetailsScreenState> = _screenState.asStateFlow()

    // метод при открытии экрана
    fun loadAppDetails(appId: String) { // добавить кеширование

        viewModelScope.launch {
            _screenState.value = AppDetailsScreenState.Loading

            try {
                val allApps = repository.getApps()
                val currentApp = repository.getAppDetails(appId)

                val similarApps = allApps.filter { it.id != appId }

                _screenState.value = AppDetailsScreenState.Success(
                    app = currentApp,
                    similarApps = similarApps
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) { // добавить обработку CancellationException
                _screenState.value =
                    AppDetailsScreenState.Error(e.message ?: "Не удалось загрузить приложение")
            }
        }
    }
}