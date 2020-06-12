package com.healiostestproject.android.screens.details

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healiostestproject.android.R
import com.healiostestproject.android.models.Comment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder

class CommentFlexible(
    val item: Comment
) : AbstractFlexibleItem<CommentFlexible.ViewHolder>() {

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.name.text = item.name
        holder.email.text = item.email
        holder.body.text = item.body
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_comment
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommentFlexible

        if (item != other.item) return false

        return true
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<out IFlexible<*>>?) :
        FlexibleViewHolder(view, adapter) {
        var name = view.findViewById<TextView>(R.id.name)
        var email = view.findViewById<TextView>(R.id.email)
        var body = view.findViewById<TextView>(R.id.body)
    }
}
