package com.suhas.factsbook.factsbook.network

import io.reactivex.Scheduler

/**
 * Created by suhas on 08/03/18.
 */
interface Scheduler {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}