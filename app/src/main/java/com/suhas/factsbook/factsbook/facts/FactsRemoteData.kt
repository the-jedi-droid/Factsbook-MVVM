package com.suhas.factsbook.factsbook.facts

import com.suhas.factsbook.factsbook.model.Facts
import io.reactivex.Flowable

class FactsRemoteData(val factsService: FactsService) : FactsDataContract.Remote {

    override fun getFacts(): Flowable<Facts> {
        return factsService.getFacts()
    }
}