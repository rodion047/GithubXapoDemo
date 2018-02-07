package com.xapo.demo.rodion.githubxapodemo.hubdetails

import com.xapo.demo.rodion.githubxapodemo.BasePresenter
import com.xapo.demo.rodion.githubxapodemo.model.Hub
import com.xapo.demo.rodion.githubxapodemo.stateData

/**
 * Implements a presenter for a repository details.
 */
class HubDetailsPresenter : BasePresenter<HubDetailsPresenter.ViewContract>() {

    /**
     * A view that binds to this presenter must implement this interface.
     */
    interface ViewContract {
        fun showHubDetails(hub: Hub)
        fun showErrorMessage(message: String)
    }

    override fun onViewAttached() {
        super.onViewAttached()
        val hub: Hub? = stateData.selectedHub
        if (hub == null) {
            getView()?.showErrorMessage("No hub data to display...")
        } else {
            getView()?.showHubDetails(hub)
        }
    }
}