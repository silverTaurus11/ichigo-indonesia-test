package com.project.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.project.myapplication.model.photo.Photo
import com.project.myapplication.model.resources.Resources
import com.project.myapplication.repository.photo.PhotosRepository
import com.project.myapplication.ui.base.BaseViewModel
import com.project.myapplication.util.Utils.loading
import com.project.myapplication.util.Utils.setError
import com.project.myapplication.util.Utils.setSchedulers
import com.project.myapplication.util.Utils.setSuccess
import com.project.myapplication.util.schedulers.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotosRepository,
    private val schedulerProvider: SchedulerProvider
): BaseViewModel() {

    val photoList = MutableLiveData<Resources<PagingData<Photo>>>()

    fun retrievePhotoData() {
        collect(repository.getPhotos()
            .setSchedulers(schedulerProvider)
            .doOnSubscribe { photoList.loading() }
            .cachedIn(viewModelScope)
            .subscribe({
                photoList.setSuccess(it)
            }, {
                photoList.setError(it)
            }))
    }
}