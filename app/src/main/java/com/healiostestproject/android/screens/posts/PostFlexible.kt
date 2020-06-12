package com.healiostestproject.android.screens.posts

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healiostestproject.android.R
import com.healiostestproject.android.models.Post
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder

class PostFlexible(
    val item: Post
) : AbstractFlexibleItem<PostFlexible.ViewHolder>() {

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.title.text = item.title
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_post
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostFlexible

        if (item != other.item) return false

        return true
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<out IFlexible<*>>?) :
        FlexibleViewHolder(view, adapter) {
        var title = view.findViewById<TextView>(R.id.title)
    }
}
