package vk.edu.android.app.ui.applist

import vk.edu.android.app.model.AppListItem

sealed interface AppListScreenState {
    object Loading : AppListScreenState
    data class Success(
        val apps: List<AppListItem>
    ) : AppListScreenState

    data class Error(val message: String) : AppListScreenState
}