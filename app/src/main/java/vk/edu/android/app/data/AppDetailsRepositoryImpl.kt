package vk.edu.android.app.data

import vk.edu.android.app.data.mapper.AppDetailsMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDetailsRepositoryImpl @Inject constructor(
    private val dataRepository: AppDetailsDataRepository,
    private val mapper: AppDetailsMapper
) : AppDetailsRepository {

    override val apps = dataRepository.apps.map { data ->
        mapper.mapToAppDetails(data)
    }
}