package com.suhas.factsbook.factsbook.common.extensions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.suhas.factsbook.factsbook.network.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe({ t: T -> data.value = t }))
    return data
}

fun <T> PublishSubject<Resource<T>>.failed(e: Throwable) {
    with(this){
        loading(false)
        onNext(Resource.failure(e))
    }
}

fun <T> PublishSubject<Resource<T>>.success(t: T) {
    with(this){
        loading(false)
        onNext(Resource.success(t))
    }
}

fun <T> PublishSubject<Resource<T>>.loading(isLoading: Boolean) {
    this.onNext(Resource.loading(isLoading))
}