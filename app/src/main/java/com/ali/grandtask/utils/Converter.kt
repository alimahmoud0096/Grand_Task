package com.ali.grandtask.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ali.grandtask.domain.models.RedditItemData
import com.google.gson.Gson
@ProvidedTypeConverter
class Converter {

    @TypeConverter
    fun StringToRedditItemData(string: String?): RedditItemData? {
        string?.let {
            return Gson().fromJson(it,RedditItemData::class.java)
        }
        return null
    }

    @TypeConverter
    fun RedditItemDataToString(data: RedditItemData?): String? {

        data?.let {
            return  Gson().toJson(it)
        }
        return null
    }
}