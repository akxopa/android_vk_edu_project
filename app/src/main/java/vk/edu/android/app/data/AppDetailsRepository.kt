package vk.edu.android.app.data

import vk.edu.android.app.model.AppDetails

interface AppDetailsRepository {
    val apps: List<AppDetails>
}