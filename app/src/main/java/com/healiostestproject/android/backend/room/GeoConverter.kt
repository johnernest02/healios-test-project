package com.healiostestproject.android.backend.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.healiostestproject.android.models.Geo

class GeoConverter {

    @TypeConverter
    fun toGeo(value: String?): Geo {
        if (value == null || value.isEmpty()) {
            return Geo()
        }

        return Gson().fromJson(value, Geo::class.java)
    }

    @TypeConverter
    fun toString(value: Geo?): String {
        if (value == null) {
            return ""
        }

        return Gson().toJson(value)
    }
}
