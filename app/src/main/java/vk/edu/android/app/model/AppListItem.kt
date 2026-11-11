package vk.edu.android.app.model

data class AppListItem(
    val id: String,
    val name: String,
    val shortDescription: String,
    val category: Category,
    val iconUrl: String
)
