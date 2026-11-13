package vk.edu.android.app.model

sealed interface AppInstallStatus {
    data object NotInstalled : AppInstallStatus

    data class Installing(
        val progress: Int
    ) : AppInstallStatus

    data object Installed : AppInstallStatus
}