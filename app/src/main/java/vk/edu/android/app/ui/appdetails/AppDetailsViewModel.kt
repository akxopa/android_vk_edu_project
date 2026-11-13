package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.assetpacks.cu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vk.edu.android.app.data.repository.AppDetailsRepository
import vk.edu.android.app.data.repository.AppInstallStateRepository
import vk.edu.android.app.model.AppInstallStatus
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppDetailsRepository,
    private val installStateRepository: AppInstallStateRepository
) : ViewModel() {

    // начальное состояние экрана
    private val _screenState =
        MutableStateFlow<AppDetailsScreenState>(AppDetailsScreenState.Loading)
    val screenState: StateFlow<AppDetailsScreenState> = _screenState.asStateFlow()

    private var installJob: Job? = null
    private var currentAppId: String? = null

    fun loadAppDetails(appId: String) { // добавить кеширование
        cancelCurrentInstall()

        currentAppId = appId

        viewModelScope.launch {
            _screenState.value = AppDetailsScreenState.Loading

            try {
                val allApps = repository.getApps()
                val currentApp = repository.getAppDetails(appId)

                val similarApps = allApps.filter { it.id != appId }

                val installStatus = installStateRepository.getInstallState(appId)

                _screenState.value = AppDetailsScreenState.Success(
                    app = currentApp,
                    similarApps = similarApps,
                    installStatus = installStatus
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

        val appId = currentState.app.id

        when (currentState.installStatus) {

            is AppInstallStatus.Installing -> {
                installJob?.cancel()
                installJob = null

                updateInstallStatus(
                    appId,
                    AppInstallStatus.NotInstalled
                )
            }

            AppInstallStatus.NotInstalled -> {
                updateInstallStatus(
                    appId,
                    AppInstallStatus.Installing(0)
                )

                installJob = viewModelScope.launch {
                    emulateInstall(appId)
                }
            }

            AppInstallStatus.Installed -> {
                // Приложение уже установлено.
                // Повторная установка не запускается.
            }
        }

    }

    private suspend fun emulateInstall(appId: String) {
        for (progress in 5..100 step 5) {
            delay(150)

            updateInstallStatus(
                appId,
                AppInstallStatus.Installing(progress)
            )
        }

        updateInstallStatus(
            appId,
            AppInstallStatus.Installed
        )

        installJob = null
    }

    private fun updateInstallStatus(
        appId: String,
        status: AppInstallStatus
    ) {
        installStateRepository.setInstallState(appId, status)

        // обновляем состояние текущего экрана,
        // только если на нём открыто нужное приложение.
        _screenState.update { state ->
            if (
                state is AppDetailsScreenState.Success &&
                state.app.id == appId
            ) {
                state.copy(installStatus = status)
            } else {
                state
            }
        }
    }

    private fun cancelCurrentInstall() {
        installJob?.cancel()
        installJob = null

        val appId = currentAppId ?: return

        // если установка была прервана переключением карточки,
        // возвращаем приложение в исходное состояние.
        if (
            installStateRepository.getInstallState(appId)
                    is AppInstallStatus.Installing
        ) {
            updateInstallStatus(
                appId,
                AppInstallStatus.NotInstalled
            )
        }
    }

    override fun onCleared() {
        cancelCurrentInstall()
        super.onCleared()
    }

}