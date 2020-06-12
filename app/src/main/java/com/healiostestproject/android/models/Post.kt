package com.healiostestproject.android.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@Entity
@PaperParcel
data class Post(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelPost.CREATOR
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
