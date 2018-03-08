package com.suhas.factsbook.factsbook.facts.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.suhas.factsbook.factsbook.common.extensions.toLiveData
import com.suhas.factsbook.factsbook.facts.FactsDataContract
import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Outcome
import io.reactivex.disposables.CompositeDisposable

class FactsViewModel(private val repository: FactsDataContract.Repository, private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val postsOutcome: LiveData<Outcome<Facts>> by lazy {
        repository.postFetchOutcome.toLiveData(compositeDisposable)
    }

    fun getFacts() {
        repository.getFacts()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}