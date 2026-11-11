package vk.edu.android.app.data.mapper

import vk.edu.android.app.data.dto.AppDetailsData
import vk.edu.android.app.data.dto.remote.AppDetailsDto
import vk.edu.android.app.data.dto.remote.CatalogItemDto
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem

interface AppRemoteMapper {
    fun mapToAppDetails(dto: AppDetailsDto): AppDetails

    fun mapToAppListItem(dto: CatalogItemDto): AppListItem
}