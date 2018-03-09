package com.suhas.factsbook.factsbook.facts

import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Resource
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface FactsDataContract {
    interface Repository {
        val fetchResource: PublishSubject<Resource<Facts>>
        fun getFacts()
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getFacts(): Flowable<Facts>
    }
}