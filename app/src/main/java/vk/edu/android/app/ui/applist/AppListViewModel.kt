package vk.edu.android.app.ui.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import vk.edu.android.app.data.AppDetailsRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppDetailsRepository
) : ViewModel() {

    // Начальное состояние
    private val _screenStateAppList =
        MutableStateFlow<AppListScreenState>(AppListScreenState.Loading)
    val screenStateAppList: StateFlow<AppListScreenState> = _screenStateAppList.asStateFlow()

    private val _uiEvent = Channel<AppListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch {
            _screenStateAppList.value = AppListScreenState.Loading // передаем текущее состояние
            try {
                val allApps = repository.getApps()

                Log.d("AppListViewModel", "Получено приложений: ${allApps.size}")
                allApps.forEach{ app ->
                    Log.d(
                        "AppListViewModel", "Приложение: ${app.name}, ID: ${app.id}"
                    )
                }

                _screenStateAppList.value =
                    if (allApps.isEmpty()) {
                        AppListScreenState.Error("Список приложений пуст")
                    } else {
                        AppListScreenState.Success(apps = allApps)
                    }

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("AppListViewModel", "Ошибка загрузки каталога", e)
                _screenStateAppList.value =
                    AppListScreenState.Error(
                        "Не удалось загрузить данные: " +
                                (e.localizedMessage ?: "Неизвестная ошибка")
                    )
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _uiEvent.send(AppListUiEvent.ShowSnackbar("Toolbar"))
        }
    }
}