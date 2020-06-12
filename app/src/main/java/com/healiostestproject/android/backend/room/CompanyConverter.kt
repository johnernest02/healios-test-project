package com.healiostestproject.android.backend.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.healiostestproject.android.models.Company

class CompanyConverter {

    @TypeConverter
    fun toCompany(value: String?): Company {
        if (value == null || value.isEmpty()) {
            return Company()
        }

        return Gson().fromJson(value, Company::class.java)
    }

    @TypeConverter
    fun toString(value: Company?): String {
        if (value == null) {
            return ""
        }

        return Gson().toJson(value)
    }
}
