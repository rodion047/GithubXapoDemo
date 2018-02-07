package com.xapo.demo.rodion.githubxapodemo

import com.xapo.demo.rodion.githubxapodemo.model.Hub


/**
 * Singleton state data instance.
 */
val stateData by lazy { StateData() }

/**
 * Holds application current state.
 */
class StateData {
    var selectedHub: Hub? = null
}
