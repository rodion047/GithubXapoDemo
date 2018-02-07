package com.xapo.demo.rodion.githubxapodemo

/**
 * Base presenter class.
 */
open class BasePresenter<T> {

    private var view: T? = null

    fun getView(): T? {
        return view
    }

    fun attachView(view: T) {
        this.view = view
        onViewAttached()
    }

    fun detachView() {
        view = null
        onViewDetached()
    }

    /**
     * Called when the specified view is attached to this presenter.
     */
    open fun onViewAttached() {}

    /**
     * Called when the view is detached from this presenter.
     */
    open fun onViewDetached() {}
}