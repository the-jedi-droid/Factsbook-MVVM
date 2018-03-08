package com.suhas.factsbook.factsbook.facts

import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Outcome
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface FactsDataContract {
    interface Repository {
        val postFetchOutcome: PublishSubject<Outcome<Facts>>
        fun getFacts()
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getFacts(): Flowable<Facts>
    }
}