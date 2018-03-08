package com.suhas.factsbook.factsbook.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by suhas on 08/03/18.
 */
class AppScheduler : Scheduler {

    override fun mainThread(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.io()
    }
}