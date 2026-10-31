package vk.edu.android.app.ui.appdetails

import vk.edu.android.app.model.AppDetails

sealed interface AppDetailsScreenState {
    object Loading : AppDetailsScreenState
    data class Success(
        val app: AppDetails,
        val similarApps: List<AppDetails>
    ) : AppDetailsScreenState

    data class Error(val message: String) : AppDetailsScreenState
}