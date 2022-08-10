package com.project.myapplication.data.api

import com.project.myapplication.model.photo.Photo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * created by Gayuh Nurul H.
 */

interface UnsplashApi {
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String): Single<List<Photo>>
}