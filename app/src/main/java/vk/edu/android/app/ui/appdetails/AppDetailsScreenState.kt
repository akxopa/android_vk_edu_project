package vk.edu.android.app.ui.appdetails

import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppInstallStatus
import vk.edu.android.app.model.AppListItem

sealed interface AppDetailsScreenState {
    object Loading : AppDetailsScreenState
    data class Success(
        val app: AppDetails,
        val similarApps: List<AppListItem>,
        val installStatus: AppInstallStatus = AppInstallStatus.NotInstalled
    ) : AppDetailsScreenState

    data class Error(val message: String) : AppDetailsScreenState
}