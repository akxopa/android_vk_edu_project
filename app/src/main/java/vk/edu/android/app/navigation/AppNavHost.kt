package vk.edu.android.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import vk.edu.android.app.ui.appdetails.AppDetailsScreen
import vk.edu.android.app.ui.appdetails.AppDetailsViewModel
import vk.edu.android.app.ui.applist.AppListScreen
import vk.edu.android.app.ui.applist.AppListViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppNavHost() { // Дополнить состоянияем загрузки и ошибки
    // контроллер. Хранит стэк экранов
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.APP_LIST // стартовый экран
    ) {
        composable(AppRoutes.APP_LIST) {
            val listViewModel: AppListViewModel = hiltViewModel()

            AppListScreen(
                viewModel = listViewModel,
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
            val appId = requireNotNull(
                backStackEntry.arguments?.getString("appId")
            ) {
                "Не передан appId для экрана подробностей"
            }

            val viewModel: AppDetailsViewModel = hiltViewModel()

            LaunchedEffect(appId) {
                viewModel.loadAppDetails(appId)
            }

            AppDetailsScreen(
                viewModel = viewModel,
                onAppClick = { newAppId ->
                    // рекурсивный переход к следующей карточке
                    navController.navigate(AppRoutes.appDetails(newAppId))
                },
                onBack = {
                    // удаляем текущий экран приложения из стека
                    navController.popBackStack()
                },
                onInstallClick = {}
            )
        }
    }
}