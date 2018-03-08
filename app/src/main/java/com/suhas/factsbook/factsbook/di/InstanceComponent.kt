package com.suhas.factsbook.factsbook.di

import com.suhas.factsbook.factsbook.facts.ui.FactsActivity
import dagger.Component

@PerInstance
@Component(dependencies = [AppComponent::class])
interface InstanceComponent {
    fun inject(factsActivity: FactsActivity)
}