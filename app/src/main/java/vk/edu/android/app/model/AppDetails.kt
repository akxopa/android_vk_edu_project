package vk.edu.android.app.model

data class AppDetails(
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
