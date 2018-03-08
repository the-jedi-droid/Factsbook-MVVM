package com.suhas.factsbook.factsbook

import android.app.Application
import com.facebook.stetho.Stetho
import com.suhas.factsbook.factsbook.di.*

class FactsBookApp : Application() {

    lateinit var appComponent: AppComponent

    val instanceComponent: InstanceComponent
        get() = DaggerInstanceComponent.builder().appComponent(appComponent).build()

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

}