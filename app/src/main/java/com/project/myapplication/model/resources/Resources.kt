package com.project.myapplication.model.resources

/**
 * created by silver taurus
 */

sealed class Resources<out R> {
    object Loading : Resources<Nothing>()
    data class Success<out T>(val data: T) : Resources<T>()
    data class Error(val errorException: Throwable) : Resources<Nothing>()
}
