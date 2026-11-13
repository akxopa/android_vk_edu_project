package vk.edu.android.app.data.repository

import vk.edu.android.app.data.mapper.AppRemoteMapper
import vk.edu.android.app.data.remote.AppRemoteDataSource
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource,
    private val mapper: AppRemoteMapper
) : AppDetailsRepository {

    override suspend fun getApps(): List<AppListItem> {
        return remoteDataSource.getCatalog().map {
            dto -> mapper.mapToAppListItem(dto)
        }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        val dto = remoteDataSource.getAppDetails(id)
        return mapper.mapToAppDetails(dto)
    }
}