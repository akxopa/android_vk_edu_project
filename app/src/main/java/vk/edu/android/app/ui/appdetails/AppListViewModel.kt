package vk.edu.android.app.ui.appdetails

import androidx.compose.material3.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import vk.edu.android.app.data.AppDetailsRepository
import vk.edu.android.app.model.AppDetails

class AppListViewModel : ViewModel() {
    val apps: List<AppDetails> = AppDetailsRepository.apps
    private val _uiEvent = Channel<AppListUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onToolbarClick() {
        viewModelScope.launch {
            _uiEvent.send(AppListUiEvent.ShowSnackbar("Toolbar"))
        }
    }
}