package com.project.myapplication.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {

    private val disposes: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        if (!disposes.isDisposed) disposes.dispose()
        super.onCleared()
    }

    fun collect(disposable: Disposable) {
        disposes.add(disposable)
    }
}