package vk.edu.android.app.ui.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.AppDetails

class AppListViewModel : ViewModel() {
    val apps: List<AppDetails> = AppDetailsRepository.apps

    // Начальное состояние
    private val _screenStateAppList =
        MutableStateFlow<AppListScreenState>(AppListScreenState.Loading)
    val screenStateAppList : StateFlow<AppListScreenState> = _screenStateAppList.asStateFlow()

    // Жмякаем на toolbar тольто тогда: кода есть что показывать
    private val _uiEvent = Channel<AppListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadApps()
    }

    fun loadApps() {
        viewModelScope.launch { // запускаем корутину в фоновом процессе
            _screenStateAppList.value = AppListScreenState.Loading // передаем текущее состояние
            try {
                val data = AppDetailsRepository.apps
                if (data.isEmpty()) {
                    _screenStateAppList.value = AppListScreenState.Error("Список приложений пуст")
                } else {
                    _screenStateAppList.value = AppListScreenState.Success(apps = data)
                }
            } catch (e: Exception) {
                _screenStateAppList.value = AppListScreenState.Error("Не удалось загрузить данные: ${e.localizedMessage}")
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _uiEvent.send(AppListUiEvent.ShowSnackbar("Toolbar"))
        }
    }
}