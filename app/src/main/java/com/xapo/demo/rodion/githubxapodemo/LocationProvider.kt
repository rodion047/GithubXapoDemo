package com.xapo.demo.rodion.githubxapodemo

import android.content.Context
import android.content.Intent
import com.xapo.demo.rodion.githubxapodemo.hubdetails.HubDetailsActivity

/**
 * Provides view navigation functionality.
 */
class LocationProvider {

    private var context: Context = MainApplication.applicationContext()

    enum class Location {
        REPO_DETAILS
    }

    fun navigateTo(location: Location) {
        when (location) {
            Location.REPO_DETAILS -> context.startActivity(Intent(context, HubDetailsActivity::class.java))
        }
    }
}
