package vk.edu.android.app.data.mapper

import vk.edu.android.app.dto.AppDetailsData
import vk.edu.android.app.model.AppDetails
import javax.inject.Inject

class AppDetailsMapperImpl @Inject constructor() : AppDetailsMapper {
    override fun mapToAppDetails(data: AppDetailsData): AppDetails {
        return AppDetails(
            id = data.id,
            name = data.name,
            developer = data.developer,
            category = data.category,
            ageRating = data.ageRating,
            size = data.size,
            iconRes = data.iconRes,
            screenshotResList = data.screenshotResList,
            shortDescription = data.shortDescription,
            description = data.description
        )
    }
}