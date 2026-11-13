package vk.edu.android.app.data.repository

import vk.edu.android.app.model.AppInstallStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInstallStateRepositoryImpl @Inject constructor() : AppInstallStateRepository {
    private val installState = mutableMapOf<String, AppInstallStatus>()

    override fun getInstallState(appId: String): AppInstallStatus {
        return installState[appId] ?: AppInstallStatus.NotInstalled
    }

    override fun setInstallState(
        appId: String,
        state: AppInstallStatus
    ) {
        installState[appId] = state
    }

}