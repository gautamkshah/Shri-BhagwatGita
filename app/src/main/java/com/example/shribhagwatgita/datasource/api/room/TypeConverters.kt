package com.example.shribhagwatgita.datasource.api.room

import androidx.room.TypeConverter
import com.example.shribhagwatgita.models.Commentary
import com.example.shribhagwatgita.models.Translation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken










class AppTypeConverters {

    @TypeConverter
    fun fromListtoString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringtoList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }


    @TypeConverter
    fun fromTranslationListToString(list: List<Translation>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToTranslationList(value: String): List<Translation> {
        return Gson().fromJson(value, object : TypeToken<List<Translation>>() {}.type)
    }

    @TypeConverter
    fun fromCommentaryListToString(list: List<Commentary>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToCommentaryList(value: String): List<Commentary> {
        return Gson().fromJson(value, object : TypeToken<List<Commentary>>() {}.type)
    }
}