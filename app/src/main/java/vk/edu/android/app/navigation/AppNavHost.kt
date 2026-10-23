package vk.edu.android.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import vk.edu.android.app.ui.appdetails.AppDetailsScreen
import vk.edu.android.app.ui.appdetails.AppListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.APP_LIST
    ) {
        composable(AppRoutes.APP_LIST) {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate(
                        AppRoutes.appDetails(appId)
                    )
                }
            )
        }

        composable(
            route = AppRoutes.APP_DETAILS,
            arguments = listOf(
                navArgument("appId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments
                ?.getString("appId")
                .orEmpty()

            AppDetailsScreen(
                appId = appId,
                onAppClick = { newAppId ->
                    navController.navigate(AppRoutes.appDetails(newAppId))
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}