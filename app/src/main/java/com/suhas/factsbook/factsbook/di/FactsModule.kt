package com.suhas.factsbook.factsbook.di

import com.suhas.factsbook.factsbook.facts.FactsDataContract
import com.suhas.factsbook.factsbook.facts.FactsRemoteData
import com.suhas.factsbook.factsbook.facts.FactsRepository
import com.suhas.factsbook.factsbook.facts.FactsService
import com.suhas.factsbook.factsbook.facts.ui.FactsAdapter
import com.suhas.factsbook.factsbook.network.Scheduler
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class FactsModule {

    @Singleton
    @Provides
    fun factsAdapter() : FactsAdapter = FactsAdapter()

    @Singleton
    @Provides
    fun remoteData(factsService: FactsService): FactsDataContract.Remote = FactsRemoteData(factsService)

    @Singleton
    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun postService(retrofit: Retrofit): FactsService = retrofit.create(FactsService::class.java)

    @Singleton
    @Provides
    fun factsRepo(remote: FactsDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable) : FactsRepository
            = FactsRepository(remote, scheduler, compositeDisposable)

}