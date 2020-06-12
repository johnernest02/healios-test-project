package com.healiostestproject.android.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.healiostestproject.android.backend.room.AddressConverter
import com.healiostestproject.android.backend.room.CompanyConverter

@Entity
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @TypeConverters(AddressConverter::class) val address: Address,
    val phone: String,
    val website: String,
    @TypeConverters(CompanyConverter::class) val company: Company
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
