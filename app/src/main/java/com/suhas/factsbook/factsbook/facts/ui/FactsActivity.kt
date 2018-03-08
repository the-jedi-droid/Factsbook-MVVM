package com.suhas.factsbook.factsbook.facts.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.suhas.factsbook.factsbook.R
import kotlinx.android.synthetic.main.activity_facts.*

class FactsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)
    }
}