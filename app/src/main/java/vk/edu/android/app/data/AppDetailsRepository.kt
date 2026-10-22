package vk.edu.android.app.data

import vk.edu.android.app.R
import vk.edu.android.app.model.AppDetails
import vk.edu.android.app.model.Category


object AppDetailsRepository {
    val apps = listOf(
        AppDetails(
            id = "1",
            name = "Гильдия Героев",
            developer = "VK Play",
            category = Category.GAME,
            ageRating = 12,
            size = 223.7f,
            iconRes = R.drawable.ic_heroes,
            screenshotResList = listOf<Int>(
                R.drawable.ic_heroes_sc1,
                R.drawable.ic_heroes_sc2,
                R.drawable.ic_heroes_sc3
            ),
            shortDescription = "Фэнтези RPG с героями и эпическими сражениями.",
            description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!"
        ),
        AppDetails(
            id = "2",
            name = "Погода",
            developer = "todayweather",
            category = Category.APP,
            ageRating = 0,
            size = 182.0f,
            iconRes = R.drawable.ic_weather,
            screenshotResList = listOf<Int>(
                R.drawable.ic_weather_sc1,
                R.drawable.ic_weather_sc2,
                R.drawable.ic_weather_sc3
            ),
            shortDescription = "Прогноз погоды",
            description = "Прогноз погоды, температура и осадки на каждый день."
        ),
        AppDetails(
            id = "3",
            name = "Мои заметки",
            developer = "mobli",
            category = Category.PRODUCTIVITY,
            ageRating = 0,
            size = 460.2f,
            iconRes = R.drawable.ic_notes,
            screenshotResList = listOf(
                R.drawable.ic_notes_sc1,
                R.drawable.ic_notes_sc1,
                R.drawable.ic_notes_sc1
            ),
            shortDescription = "Простой и быстрый способ делать заметки.",
            description = "Создавай заметки и сохраняй важные мысли. С помощью заметок вы сможете эффективно управлять важной информацией и делами."
        ),
        AppDetails(
            id = "4",
            name = "Курлыкурлы",
            developer = "dap",
            category = Category.SOCIAL,
            ageRating = 0,
            size = 182.0f,
            iconRes = R.drawable.ic_kurlykurly,
            screenshotResList = listOf<Int>(
                R.drawable.ic_kurlykurly_sc1,
                R.drawable.ic_kurlykurly_sc1,
                R.drawable.ic_kurlykurly_sc1
            ),
            shortDescription = "Отправляй сообщения, делись фото и не теряйся в бесконечных уведомлениях.",
            description = "Курлы-курлы: быстро, просто, без перегруза. Отправляй сообщения, делись фото и не теряйся в бесконечных уведомлениях. Мы сделали интерфейс таким, чтобы ты сразу находил нужное и не отвлекался на лишнее. Твой спокойный канал связи — всегда под рукой"
        ),
        AppDetails(
            id = "5",
            name = "Твори сам",
            developer = "dap",
            category = Category.EDUCATION,
            ageRating = 0,
            size = 182.0f,
            iconRes = R.drawable.ic_made_yourself,
            screenshotResList = listOf<Int>(
                R.drawable.ic_made_yourself_sc1,
                R.drawable.ic_made_yourself_sc1,
                R.drawable.ic_made_yourself_sc1
            ),
            shortDescription = "Создай вещи для дома своими руками.",
            description = "Твори сам — поможет создать вещи для дома своими руками. В приложении — понятные мастер‑классы, списки материалов и пошаговые инструкции: от простых поделок до уютных предметов интерьера. Вдохновляйся и делай с удовольствием!"

        ),
        AppDetails(
            id = "6",
            name = "Моя музыка",
            developer = "dap",
            category = Category.MUSIC,
            ageRating = 0,
            size = 230.0f,
            iconRes = R.drawable.ic_my_music,
            screenshotResList = listOf<Int>(
                R.drawable.ic_my_music_sc1,
                R.drawable.ic_my_music_sc1,
                R.drawable.ic_my_music_sc1
            ),
            shortDescription = "Слушай, пой, исполняй.",
            description = "Моя музыка: слушай любимые треки, собирай персональные плейлисты и сохраняй музыку для офлайн‑прослушивания. Твой музыкальный мир — в одном приложении."

        ),
        AppDetails(
            id = "7",
            name = "Простой монтаж",
            developer = "dap",
            category = Category.VIDEO,
            ageRating = 0,
            size = 530.0f,
            screenshotResList = listOf<Int>(
                R.drawable.ic_easy_movie_sc1,
                R.drawable.ic_easy_movie_sc1,
                R.drawable.ic_easy_movie_sc1
            ),
            iconRes = R.drawable.ic_easy_movie,
            shortDescription = "Создавай видеоролики в два клика.",
            description = "Создавай короткие видеоролики за минуты — даже если ты новичок. “Простой монтаж” даёт всё необходимое без лишнего: обрезка, склейка, переходы, музыка и субтитры. Никаких сложных панелей и запутанных меню: только понятные инструменты и быстрый результат. Идеально для соцсетей, историй и личных роликов. Скачивай и монтируй уже сегодня!"
        ),
        AppDetails(
            id = "8",
            name = "Готов к лету",
            developer = "dap",
            category = Category.HEALTH,
            ageRating = 0,
            size = 530.0f,
            iconRes = R.drawable.ic_ready_to_summer,
            screenshotResList = listOf<Int>(
                R.drawable.ic_ready_to_summer_sc1,
                R.drawable.ic_ready_to_summer_sc1,
                R.drawable.ic_ready_to_summer_sc1
            ),
            shortDescription = "Личный тренер у тебя в телефоне. Накачайся к лету",
            description = "Хочешь быть в форме к лету? “Готов к лету” — твой личный тренер в телефоне. Приложение подберёт план тренировок под твой уровень: от первых шагов до уверенного прогресса. Короткие, но эффективные занятия, понятные видеоинструкции и трекер результатов — чтобы ты видел, как становишься сильнее с каждой неделей. Никаких сложных терминов и перегруженных программ: только то, что реально работает. Начни сегодня — и встреть лето в лучшей форме!"
        ),
        AppDetails(
            id = "9",
            name = "Драйтулинг",
            developer = "akxopa",
            category = Category.SPORTS,
            ageRating = 0,
            size = 530.0f,
            iconRes = R.drawable.ic_drytool,
            screenshotResList = listOf<Int>(
                R.drawable.ic_drytool_sc1,
                R.drawable.ic_drytool_sc1,
                R.drawable.ic_drytool_sc1
            ),
            shortDescription = "Создавай трассы. Лезь. Делись с друзьями",
            description = "Драйтулинг: отмечай зацепки, создавай трассы и делись ими с друзьями. Фиксируй пролазы, чтобы отслеживать прогресс. Удобное приложение для любителей драйтулинга и скалолазания. Планируй маршруты и тренируйся эффективнее!"

        )
    )
}