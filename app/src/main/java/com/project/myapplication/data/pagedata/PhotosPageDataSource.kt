package com.project.myapplication.data.pagedata

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.project.myapplication.data.api.UnsplashApi
import com.project.myapplication.model.photo.Photo
import com.project.myapplication.util.Constant
import com.project.myapplication.util.schedulers.SchedulerProvider
import io.reactivex.Single

class PhotosPageDataSource (
    private val unsplashApi: UnsplashApi,
    private val schedulerProvider: SchedulerProvider): RxPagingSource<Int, Photo>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Photo>> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return unsplashApi.getPhotos(position, params.loadSize, Constant.Order.LATEST)
            .subscribeOn(schedulerProvider.ioScheduler())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Photo>, position: Int): LoadResult<Int, Photo> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else position.plus(1)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}