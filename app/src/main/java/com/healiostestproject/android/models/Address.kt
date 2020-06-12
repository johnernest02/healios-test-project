package com.healiostestproject.android.models

import androidx.room.TypeConverters
import com.healiostestproject.android.backend.room.GeoConverter

data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
    val zipcode: String = "",
    @TypeConverters(GeoConverter::class) val geo: Geo = Geo()
)
