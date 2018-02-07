package com.xapo.demo.rodion.githubxapodemo.hublist

import com.xapo.demo.rodion.githubxapodemo.BasePresenter
import com.xapo.demo.rodion.githubxapodemo.LocationProvider
import com.xapo.demo.rodion.githubxapodemo.model.Hub
import com.xapo.demo.rodion.githubxapodemo.service.GitHubService
import com.xapo.demo.rodion.githubxapodemo.stateData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * HitHub repositories presenter.
 */
class HubListPresenter : BasePresenter<HubListPresenter.ViewContract>() {

    /**
     * A view that binds to this presenter must implement this interface.
     */
    interface ViewContract {
        fun showErrorMessage(message: String)
        fun showRepos(hubs: List<Hub>)
    }

    private var hubList: List<Hub>? = null

    /**
     * Display hub list once attached to a view.
     */
    override fun onViewAttached() {
        super.onViewAttached()

        val list = hubList
        if (list != null) {
            showHubs(list)
        } else {
            loadHubs()
        }
    }

    /**
     * Loads public hubs from the internet.
     */
    private fun loadHubs() {
        val apiService = GitHubService.create()
        apiService.getPublicHubs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    showHubs(result)
                }, { error ->
                    error.printStackTrace()
                    showErrorMessage()
                })
    }

    private fun showErrorMessage() {
        getView()?.showErrorMessage("Failed to get list of hubs")
    }

    private fun showHubs(hubs: List<Hub>) {
        getView()?.showRepos(hubs)
    }

    fun onRepoSelected(hub: Hub) {
        stateData.selectedHub = hub
        LocationProvider().navigateTo(LocationProvider.Location.REPO_DETAILS)
    }
}