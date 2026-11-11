package vk.edu.android.app.data.mapper

import vk.edu.android.app.data.dto.remote.AppDetailsDto
import vk.edu.android.app.data.dto.remote.CatalogItemDto
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.AppListItem
import vk.edu.android.app.model.Category
import vk.edu.android.app.model.toDisplayName
import javax.inject.Inject

class AppRemoteMapperImpl @Inject constructor() : AppRemoteMapper {
    override fun mapToAppDetails(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            id = dto.id,
            name = dto.name,
            developer = dto.developer,
            category = mapCategory(dto.category),
            ageRating = dto.ageRating,
            size = dto.size.toFloat(),
            iconUrl = dto.iconUrl,
            screenshotUrlList = dto.screenshotUrlList,
            description = dto.description
        )
    }

    override fun mapToAppListItem(dto: CatalogItemDto): AppListItem {
        return AppListItem(
            id = dto.id,
            name = dto.name,
            shortDescription = dto.description,
            category = mapCategory(dto.category),
            iconUrl = dto.iconUrl
        )
    }

    private fun mapCategory(categoryName: String): Category {
        return Category.entries.firstOrNull {
            it.toDisplayName().equals(categoryName.trim(), ignoreCase = true)
        } ?: Category.UNKNOWN
    }
}