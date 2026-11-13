package vk.edu.android.app.data.repository

import vk.edu.android.app.model.AppInstallStatus

interface AppInstallStateRepository {
    fun getInstallState(appId: String): AppInstallStatus

    fun setInstallState(
        appId: String,
        state: AppInstallStatus
    )
}