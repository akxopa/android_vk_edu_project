package vk.edu.android.app.ui.applist

import vk.edu.android.app.model.AppDetails

sealed interface AppListScreenState {
    object Loading : AppListScreenState
    data class Success(
        val apps: List<AppDetails>
    ) : AppListScreenState

    data class Error(val message: String) : AppListScreenState
}