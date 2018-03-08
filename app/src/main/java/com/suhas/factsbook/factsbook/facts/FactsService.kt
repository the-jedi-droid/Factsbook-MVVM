package com.suhas.factsbook.factsbook.facts

import com.suhas.factsbook.factsbook.model.Facts
import io.reactivex.Flowable
import retrofit2.http.GET

interface FactsService {

    @GET("s/2iodh4vg0eortkl/factsLiveData.json")
    fun getFacts(): Flowable<Facts>
}