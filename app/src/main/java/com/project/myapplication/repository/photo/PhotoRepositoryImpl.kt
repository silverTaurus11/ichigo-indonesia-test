package com.project.myapplication.repository.photo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.project.myapplication.model.photo.Photo
import com.project.myapplication.data.pagedata.PhotosPageDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val pageDataSource: PhotosPageDataSource
): PhotosRepository {

    override fun getPhotos(): Flowable<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 20),
            pagingSourceFactory = { pageDataSource }
        ).flowable
    }
}