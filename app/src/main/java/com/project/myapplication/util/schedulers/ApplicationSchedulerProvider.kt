package com.project.myapplication.util.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApplicationSchedulerProvider: SchedulerProvider {
    override fun ioScheduler(): Scheduler {
        return Schedulers.io()
    }

    override fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}