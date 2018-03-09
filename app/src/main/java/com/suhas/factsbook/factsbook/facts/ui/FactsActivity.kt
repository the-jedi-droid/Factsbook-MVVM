package com.suhas.factsbook.factsbook.facts.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.Toast
import com.suhas.factsbook.factsbook.FactsBookApp
import com.suhas.factsbook.factsbook.R
import com.suhas.factsbook.factsbook.facts.vm.FactsViewModel
import com.suhas.factsbook.factsbook.facts.vm.FactsViewModelFactory
import com.suhas.factsbook.factsbook.model.Facts
import com.suhas.factsbook.factsbook.network.Resource
import kotlinx.android.synthetic.main.activity_facts.*
import java.io.IOException
import javax.inject.Inject

class FactsActivity : AppCompatActivity() {

    @Inject
    lateinit var factsAdapter: FactsAdapter

    @Inject
    lateinit var factsViewModelFactory: FactsViewModelFactory

    private val factsViewModel: FactsViewModel by lazy {
        ViewModelProviders.of(this, factsViewModelFactory).get(FactsViewModel::class.java)
    }

    private val context: Context by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        (application as FactsBookApp).instanceComponent.inject(this)

        setToolBar(resources.getString(R.string.app_name))

        factsList.adapter = factsAdapter
        factsList.layoutManager = StaggeredGridLayoutManager(1, 1)

        factsViewModel.getFacts()
        loadFacts()

        swipeToRefreshLayout.setOnRefreshListener { factsViewModel.getFacts() }
    }

    private fun loadFacts() {
        factsViewModel.factsResource.observe(this, Observer<Resource<Facts>> {
            when (it) {
                is Resource.Progress -> swipeToRefreshLayout.isRefreshing = it.loading

                is Resource.Success -> {
                    Log.d("==>", "inside onSuccess")
                    setToolBar(it.data.title)
                    factsList.recycledViewPool.clear()
                    (factsList.adapter as FactsAdapter).rows = it.data.rows
                }

                is Resource.Failure -> {
                    Log.d("==>", "inside failure")
                    if (it.e is IOException)
                        Toast.makeText(context, R.string.need_internet_posts, Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(context, R.string.failed_post_try_again, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setToolBar(title: String?) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }
}