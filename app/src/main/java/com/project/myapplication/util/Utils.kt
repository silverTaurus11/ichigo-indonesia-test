package com.project.myapplication.util

import androidx.lifecycle.MutableLiveData
import com.project.myapplication.model.resources.Resources
import com.project.myapplication.util.schedulers.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Observable

object Utils {

    fun <T>Observable<T>.setSchedulers(schedulerProvider: SchedulerProvider): Observable<T> {
        return this.subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
    }

    fun <T>Flowable<T>.setSchedulers(schedulerProvider: SchedulerProvider): Flowable<T> {
        return this.subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
    }

    fun <T>MutableLiveData<Resources<T>>.loading() {
        this.postValue(Resources.Loading)
    }

    fun <T>MutableLiveData<Resources<T>>.setSuccess(data: T) {
        this.postValue(Resources.Success(data))
    }

    fun <T>MutableLiveData<Resources<T>>.setError(throwable: Throwable) {
        this.postValue(Resources.Error(throwable))
    }

}