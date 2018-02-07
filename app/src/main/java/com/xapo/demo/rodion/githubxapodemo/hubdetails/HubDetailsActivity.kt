package com.xapo.demo.rodion.githubxapodemo.hubdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.xapo.demo.rodion.githubxapodemo.R
import com.xapo.demo.rodion.githubxapodemo.model.Hub


/**
 * Singleton presenter instance.
 */
val hubDetailsPresenter by lazy { HubDetailsPresenter() }

/**
 * Displays hub details.
 */
class HubDetailsActivity : AppCompatActivity(), HubDetailsPresenter.ViewContract {

    private var presenter: HubDetailsPresenter = hubDetailsPresenter

    private lateinit var ownerImageView: ImageView
    private lateinit var hubNameView: TextView
    private lateinit var hubDescriptionView: TextView
    private lateinit var hubUrlView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_details)

        ownerImageView = findViewById(R.id.hub_profile_image)
        hubNameView = findViewById(R.id.hub_name)
        hubDescriptionView = findViewById(R.id.hub_description)
        hubUrlView = findViewById(R.id.hub_url)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showHubDetails(hub: Hub) {
        Picasso.with(this).load(hub.owner.avatar_url).into(ownerImageView)
        hubNameView.text = hub.name
        hubDescriptionView.text = hub.description
        hubUrlView.text = hub.owner.url
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
