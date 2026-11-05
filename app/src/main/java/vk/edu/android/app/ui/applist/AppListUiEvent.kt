package vk.edu.android.app.ui.applist

sealed interface AppListUiEvent {
    data class ShowSnackbar(val message: String) : AppListUiEvent
}