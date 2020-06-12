package com.healiostestproject.android.backend.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.healiostestproject.android.models.Address

class AddressConverter {

    @TypeConverter
    fun toAddress(value: String?): Address {
        if (value == null || value.isEmpty()) {
            return Address()
        }

        return Gson().fromJson(value, Address::class.java)
    }

    @TypeConverter
    fun toString(value: Address?): String {
        if (value == null) {
            return ""
        }

        return Gson().toJson(value)
    }
}
