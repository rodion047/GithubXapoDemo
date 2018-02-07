package com.xapo.demo.rodion.githubxapodemo.hublist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.xapo.demo.rodion.githubxapodemo.R
import com.xapo.demo.rodion.githubxapodemo.inflate
import com.xapo.demo.rodion.githubxapodemo.model.Hub

/**
 * Repo list adapter.
 */
class HubListAdapter(private val listener: (Hub) -> Unit) : RecyclerView.Adapter<HubListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.hub_name)
        private val description: TextView = itemView.findViewById(R.id.hub_description)
        private val profileImage: ImageView = itemView.findViewById(R.id.hub_profile_image)

        fun bind(hub: Hub, listener: (Hub) -> Unit) = with(itemView) {
            name.text = hub.name
            description.text = hub.description
            setOnClickListener { listener(hub) }
            Picasso.with(itemView.context).load(hub.owner.avatar_url).into(profileImage)
        }
    }

    private var hubs: List<Hub> = listOf()

    /**
     * Sets list of hubs to be displayed.
     */
    fun setData(hubs: List<Hub>) {
        this.hubs = hubs
        notifyDataSetChanged()
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return hubs.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(hubs[position], listener)

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(parent.inflate(R.layout.hub_list_item))

}