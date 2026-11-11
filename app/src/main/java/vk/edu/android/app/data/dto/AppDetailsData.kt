package vk.edu.android.app.data.dto

import vk.edu.android.app.model.Category

data class AppDetailsData(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconRes: Int,
    val screenshotResList: List<Int>,
    val shortDescription: String,
    val description: String
)