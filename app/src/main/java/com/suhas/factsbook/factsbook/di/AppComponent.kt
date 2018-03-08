package com.suhas.factsbook.factsbook.di

import android.content.Context
import com.suhas.factsbook.factsbook.network.Scheduler
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class))
interface AppComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    fun scheduler(): Scheduler

}