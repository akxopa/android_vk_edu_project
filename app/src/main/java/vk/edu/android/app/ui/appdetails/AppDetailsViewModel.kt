package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.InstallState
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

    private var installJob: Job? = null

    // метод при открытии экрана
    fun loadAppDetails(appId: String) { // добавить кеширование
        installJob?.cancel() // отмена установки приложения при переходе на другую карточку.
        installJob = null

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
            } catch (e: Exception) {
                _screenState.value =
                    AppDetailsScreenState.Error(e.message ?: "Не удалось загрузить приложение")
            }
        }
    }

    fun onInstallClick() {
        val currentState = _screenState.value

        if (
            currentState !is AppDetailsScreenState.Success
        ) return

        if (
            currentState.installState is InstallState.Installing
        ) {
            installJob?.cancel()
            installJob = null

            _screenState.update { state ->
                if (state is AppDetailsScreenState.Success) {
                    state.copy(
                        installState = InstallState.NotInstalled
                    )
                } else {
                    state
                }
            }
        } else if (currentState.installState is InstallState.NotInstalled) {
            _screenState.update { state ->
                if (state is AppDetailsScreenState.Success) {
                    state.copy(
                        installState = InstallState.Installing(0)
                    )
                } else {
                    state
                }
            }

            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }


    }

    private suspend fun emulateInstall() {
        for (progress in 5..100 step 5) {
            delay(150)

            _screenState.update { state ->
                if (state is AppDetailsScreenState.Success) {
                    state.copy(
                        installState = InstallState.Installing(progress)
                    )
                } else {
                    state
                }
            }
        }

        _screenState.update { state ->
            if (state is AppDetailsScreenState.Success) {
                state.copy(
                    installState = InstallState.Installed
                )
            } else {
                state
            }
        }

        installJob = null
    }

}