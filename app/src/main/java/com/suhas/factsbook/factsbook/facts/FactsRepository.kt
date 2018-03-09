package com.suhas.factsbook.factsbook.facts


import com.suhas.factsbook.factsbook.common.extensions.*
import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Resource
import com.suhas.factsbook.factsbook.network.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class FactsRepository(private val remote: FactsDataContract.Remote,
                      private val scheduler: Scheduler,
                      private val compositeDisposable: CompositeDisposable) : FactsDataContract.Repository {

    override val fetchResource: PublishSubject<Resource<Facts>> = PublishSubject.create<Resource<Facts>>()

    override fun getFacts() {
        fetchResource.loading(true)
        remote.getFacts()
                .performOnBackOutOnMain(scheduler)
                .subscribe({
                    fetchResource.success(it)
                }, { error -> handleError(error) })
                .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        fetchResource.failed(error)
    }
}