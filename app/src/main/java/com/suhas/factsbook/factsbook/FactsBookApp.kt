package com.suhas.factsbook.factsbook

import android.app.Application
import com.facebook.stetho.Stetho
import com.suhas.factsbook.factsbook.di.AppComponent
import com.suhas.factsbook.factsbook.di.AppModule
import com.suhas.factsbook.factsbook.di.DaggerAppComponent

class FactsBookApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

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