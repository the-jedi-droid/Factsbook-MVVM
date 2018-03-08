package com.suhas.factsbook.factsbook.facts


import com.suhas.factsbook.factsbook.common.extensions.addTo
import com.suhas.factsbook.factsbook.common.extensions.failed
import com.suhas.factsbook.factsbook.common.extensions.loading
import com.suhas.factsbook.factsbook.common.extensions.performOnBackOutOnMain
import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Outcome
import com.suhas.factsbook.factsbook.network.Scheduler
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class FactsRepository(private val remote: FactsDataContract.Remote,
                      private val scheduler: Scheduler,
                      private val compositeDisposable: CompositeDisposable) : FactsDataContract.Repository {

    override val postFetchOutcome: PublishSubject<Outcome<Facts>> = PublishSubject.create<Outcome<Facts>>()

    override fun getFacts() {
        postFetchOutcome.loading(true)
        Flowable.just(remote.getFacts())
                .performOnBackOutOnMain(scheduler)
                .subscribe({}, { error -> handleError(error) })
                .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        postFetchOutcome.failed(error)
    }
}