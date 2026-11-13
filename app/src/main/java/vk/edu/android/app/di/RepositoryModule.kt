package vk.edu.android.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vk.edu.android.app.data.repository.AppDetailsRepository
import vk.edu.android.app.data.repository.AppDetailsRepositoryImpl
import vk.edu.android.app.data.repository.AppInstallStateRepository
import vk.edu.android.app.data.repository.AppInstallStateRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppDetailsRepository(
        impl: AppDetailsRepositoryImpl
    ): AppDetailsRepository

    @Binds
    abstract fun bindAppInstallStateRepository(
        impl: AppInstallStateRepositoryImpl
    ) : AppInstallStateRepository
}