package vk.edu.android.app.ui.appdetails

import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem
import vk.edu.android.app.model.InstallState

sealed interface AppDetailsScreenState {
    object Loading : AppDetailsScreenState
    data class Success(
        val app: AppDetails,
        val similarApps: List<AppListItem>,
        val installState: InstallState = InstallState.NotInstalled
    ) : AppDetailsScreenState

    data class Error(val message: String) : AppDetailsScreenState
}