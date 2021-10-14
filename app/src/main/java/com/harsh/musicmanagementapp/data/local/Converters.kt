package com.harsh.musicmanagementapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.harsh.musicmanagementapp.domain.model.Track

class Converters {

    @TypeConverter
    fun tagsToJson(value: List<Any?>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToTags(value: String) = Gson().fromJson(value, Array<String?>::class.java).toList()


    @TypeConverter
    fun tracksToJson(value: List<Track>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToTracks(value: String) = Gson().fromJson(value, Array<Track>::class.java).toList()
}