package vk.edu.android.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vk.edu.android.app.data.mapper.AppRemoteMapper
import vk.edu.android.app.data.mapper.AppRemoteMapperImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindAppDetailsMapper(
        impl: AppRemoteMapperImpl
    ): AppRemoteMapper
}