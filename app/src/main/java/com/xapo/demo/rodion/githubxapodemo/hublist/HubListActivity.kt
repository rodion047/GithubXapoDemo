package com.xapo.demo.rodion.githubxapodemo.hublist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.xapo.demo.rodion.githubxapodemo.R
import com.xapo.demo.rodion.githubxapodemo.model.Hub
import kotlinx.android.synthetic.main.activity_hub_list.*

/**
 * Singleton presenter instance.
 */
val hubListPresenter by lazy { HubListPresenter() }

class HubListActivity : AppCompatActivity(), HubListPresenter.ViewContract {

    private var presenter: HubListPresenter = hubListPresenter
    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
    private val adapter: HubListAdapter = HubListAdapter { hub ->
        onListItemSelected(hub)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_list)

        // Initialize the recycler view that displays a list of hubs
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = this.adapter
    }

    private fun onListItemSelected(hub: Hub) {
        hubListPresenter.onRepoSelected(hub)
    }

    override fun showRepos(hubs: List<Hub>) {
        adapter.setData(hubs)
        adapter.notifyDataSetChanged()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }
}
