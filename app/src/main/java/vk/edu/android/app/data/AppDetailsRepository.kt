package vk.edu.android.app.data

import vk.edu.android.app.R
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.Category


object AppDetailsRepository {
    val apps = listOf(
        AppDetails(
            id = 1,
            name = "Гильдия Героев",
            description = "Фэнтези RPG с героями и эпическими сражениями.",
            category = Category.GAME,
            iconRes = R.drawable.ic_heroes
        ),
        AppDetails(
            id = 2,
            name = "Погода",
            description = "Прогноз погоды, температура и осадки на каждый день.",
            category = Category.APP,
            iconRes = R.drawable.ic_weather
        ),
        AppDetails(
            id = 3,
            name = "Мои заметки",
            description = "Создавай заметки и сохраняй важные мысли.",
            category = Category.PRODUCTIVITY,
            iconRes = R.drawable.ic_notes
        )
    )
}