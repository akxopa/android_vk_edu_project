package vk.edu.android.app.data.mapper

import vk.edu.android.app.dto.AppDetailsData
import vk.edu.android.app.model.AppDetails

interface AppDetailsMapper {
    fun mapToAppDetails(data: AppDetailsData): AppDetails
}