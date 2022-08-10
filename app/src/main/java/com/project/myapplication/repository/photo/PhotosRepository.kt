package com.project.myapplication.repository.photo

import androidx.paging.PagingData
import com.project.myapplication.model.photo.Photo
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PhotosRepository {
    fun getPhotos(): Flowable<PagingData<Photo>>
}