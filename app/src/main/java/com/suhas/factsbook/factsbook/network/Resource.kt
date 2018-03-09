package com.suhas.factsbook.factsbook.network

sealed class Resource<T> {
    data class Progress<T>(var loading: Boolean) : Resource<T>()
    data class Success<T>(var data: T) : Resource<T>()
    data class Failure<T>(val e: Throwable) : Resource<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Resource<T> = Progress(isLoading)

        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> failure(e: Throwable): Resource<T> = Failure(e)
    }
}