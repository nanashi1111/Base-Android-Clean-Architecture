package com.cleanarchitectkotlinflowhiltsimplestway.di

import android.content.Context
import com.cleanarchitectkotlinflowhiltsimplestway.data.remote.Api
import com.cleanarchitectkotlinflowhiltsimplestway.data.repository.PhotoRepositoryImpl
import com.cleanarchitectkotlinflowhiltsimplestway.data.repository.UserRepositoryImpl
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(api: Api): UserRepository = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePhotoRepository(@ApplicationContext context: Context, api: Api): PhotoRepository = PhotoRepositoryImpl(api, context)
}