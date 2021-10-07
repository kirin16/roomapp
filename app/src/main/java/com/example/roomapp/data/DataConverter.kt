package com.example.roomapp.data

import android.util.Log
import androidx.room.TypeConverter
import com.example.roomapp.data.model.OneMoreModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<OneMoreModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<OneMoreModel>>() {}.type
        Log.e("1", gson.toJson(value, type).toString())
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<OneMoreModel> {
        val gson = Gson()
        val type = object : TypeToken<List<OneMoreModel>>() {}.type
        return gson.fromJson(value, type)
    }

}