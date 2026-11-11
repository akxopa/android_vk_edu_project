package vk.edu.android.app.data.remote

import vk.edu.android.app.data.dto.remote.AppDetailsDto
import vk.edu.android.app.data.dto.remote.CatalogItemDto
import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val appApiService: AppApiService
) {
    suspend fun getCatalog(): List<CatalogItemDto> {
        return appApiService.getCatalog()
    }

    suspend fun getAppDetails(id: String): AppDetailsDto {
        return appApiService.getAppDetails(id)
    }
}