package vk.edu.android.app.model

sealed interface InstallState {
    data object NotInstalled : InstallState

    data class Installing(
        val progress: Int
    ) : InstallState

    data object Installed : InstallState
}