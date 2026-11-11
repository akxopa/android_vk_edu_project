package vk.edu.android.app.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import vk.edu.android.app.data.dto.remote.AppDetailsDto
import vk.edu.android.app.data.dto.remote.CatalogItemDto

interface AppApiService {
    @GET("catalog")
    suspend fun getCatalog(): List<CatalogItemDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(
        @Path("id") id: String
    ): AppDetailsDto
}