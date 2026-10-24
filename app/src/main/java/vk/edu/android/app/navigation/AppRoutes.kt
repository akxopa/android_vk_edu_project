package vk.edu.android.app.navigation

object AppRoutes {
    const val APP_LIST = "app_list"
    const val APP_DETAILS = "app_details/{appId}"

    fun appDetails(appId: String): String {
        return "app_details/$appId"
    }
}