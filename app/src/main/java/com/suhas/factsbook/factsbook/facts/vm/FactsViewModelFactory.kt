package com.suhas.factsbook.factsbook.facts.vm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider.Factory
import com.suhas.factsbook.factsbook.facts.FactsDataContract
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FactsViewModelFactory (private val repository: FactsDataContract.Repository,
                            private val compositeDisposable: CompositeDisposable) : Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FactsViewModel(repository, compositeDisposable) as T
    }
}