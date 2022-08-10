package com.project.myapplication.di

import com.project.myapplication.data.api.UnsplashApi
import com.project.myapplication.data.pagedata.PhotosPageDataSource
import com.project.myapplication.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PageDataSourceModule {

    @Provides
    fun providePhotoDataSources(unsplashApi: UnsplashApi,
                                schedulerProvider: SchedulerProvider): PhotosPageDataSource {
        return PhotosPageDataSource(unsplashApi, schedulerProvider)
    }
}