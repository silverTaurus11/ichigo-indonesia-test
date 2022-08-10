package com.project.myapplication.util.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider: SchedulerProvider {
    override fun ioScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun uiScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}