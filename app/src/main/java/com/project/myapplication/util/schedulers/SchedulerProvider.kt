package com.project.myapplication.util.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ioScheduler(): Scheduler

    fun uiScheduler(): Scheduler
}