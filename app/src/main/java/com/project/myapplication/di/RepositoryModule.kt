package com.project.myapplication.di

import com.project.myapplication.repository.photo.PhotoRepositoryImpl
import com.project.myapplication.repository.photo.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePhotosRepository(photoRepository: PhotoRepositoryImpl): PhotosRepository
}