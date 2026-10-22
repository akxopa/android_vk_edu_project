package vk.edu.android.app.model

data class AppDetails(
    val id: Int,
    val name: String,
    val description: String,
    val category: Category, // enum
    val iconRes: Int
)
