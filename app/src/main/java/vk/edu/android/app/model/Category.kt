package vk.edu.android.app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category {
    @SerialName("Приложения")
    APP,

    @SerialName("Игры")
    GAME,

    @SerialName("Производительность")
    PRODUCTIVITY,

    @SerialName("Социальные сети")
    SOCIAL,

    @SerialName("Образование")
    EDUCATION,

    @SerialName("Развлечения")
    ENTERTAINMENT,

    @SerialName("Музыка")
    MUSIC,

    @SerialName("Видео")
    VIDEO,

    @SerialName("Фотография")
    PHOTOGRAPHY,

    @SerialName("Здоровье")
    HEALTH,

    @SerialName("Спорт")
    SPORTS,

    @SerialName("Новости")
    NEWS,

    @SerialName("Книги")
    BOOKS,

    @SerialName("Бизнес")
    BUSINESS,

    @SerialName("Финансы")
    FINANCE,

    @SerialName("Путешествия")
    TRAVEL,

    @SerialName("Карты")
    MAPS,

    @SerialName("Еда")
    FOOD,

    @SerialName("Покупки")
    SHOPPING,

    @SerialName("Утилиты")
    UTILITIES;
}

fun Category.toDisplayName(): String {
    return when (this) {
        Category.APP -> "Приложения"
        Category.GAME -> "Игры"
        Category.PRODUCTIVITY -> "Производительность"
        Category.SOCIAL -> "Социальные сети"
        Category.EDUCATION -> "Образование"
        Category.ENTERTAINMENT -> "Развлечения"
        Category.MUSIC -> "Музыка"
        Category.VIDEO -> "Видео"
        Category.PHOTOGRAPHY -> "Фотография"
        Category.HEALTH -> "Здоровье"
        Category.SPORTS -> "Спорт"
        Category.NEWS -> "Новости"
        Category.BOOKS -> "Книги"
        Category.BUSINESS -> "Бизнес"
        Category.FINANCE -> "Финансы"
        Category.TRAVEL -> "Путешествия"
        Category.MAPS -> "Карты"
        Category.FOOD -> "Еда"
        Category.SHOPPING -> "Покупки"
        Category.UTILITIES -> "Утилиты"
    }
}
