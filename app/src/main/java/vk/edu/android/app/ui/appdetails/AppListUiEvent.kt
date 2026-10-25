package vk.edu.android.app.ui.appdetails

sealed interface AppListUiEvent {
    data class ShowSnackbar(val message: String) : AppListUiEvent
}