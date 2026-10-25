package vk.edu.android.app.ui.appdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.AppDetails

class AppDetailsViewModel : ViewModel() {
    private val allApps = AppDetailsRepository.apps

    // состояние текущего выбранного приложения
    private val _selectedApp = MutableStateFlow<AppDetails?>(null)
    val selectedApp: StateFlow<AppDetails?> = _selectedApp.asStateFlow()

    //  состояние списка похожих приложений
    private val _similarApps = MutableStateFlow<List<AppDetails>>(emptyList())
    val similarApps: StateFlow<List<AppDetails>> = _similarApps.asStateFlow()

    // метод при открытии экрана
    fun loadAppDetails(appId: String) {
        val currentApp = allApps.find { it.id == appId }
        _selectedApp.value = currentApp

        _similarApps.value = allApps.filter { it.id != appId } // не показываем в списке приложений текущее приложение
    }
}