package vk.edu.android.app.data

import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem

interface AppDetailsRepository {

    suspend fun getApps(): List<AppListItem>

    suspend fun getAppDetails(id: String): AppDetails
}