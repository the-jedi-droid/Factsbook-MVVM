package com.suhas.factsbook.factsbook.di

import android.content.Context
import com.suhas.factsbook.factsbook.facts.ui.FactsAdapter
import com.suhas.factsbook.factsbook.facts.vm.FactsViewModelFactory
import com.suhas.factsbook.factsbook.network.Scheduler
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class, FactsModule::class))
interface AppComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    fun scheduler(): Scheduler

    fun adapter(): FactsAdapter

    fun factsViewModelFactory() : FactsViewModelFactory

}