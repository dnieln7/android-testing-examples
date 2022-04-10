package com.dnieln7.testing.persistance.books.converters

import androidx.room.TypeConverter
import com.dnieln7.testing.model.book.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookConverters {

    @TypeConverter
    fun fromStringToAuthorList(value: String): List<Author> {
        return Gson().fromJson(
            value,
            TypeToken.getParameterized(List::class.java, Author::class.java).type
        )
    }

    @TypeConverter
    fun fromAuthorListToString(authors: List<Author>): String {
        return Gson().toJson(authors)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {
        return Gson().fromJson(
            value,
            TypeToken.getParameterized(List::class.java, String::class.java).type
        )
    }

    @TypeConverter
    fun fromStringListToString(subjects: List<String>): String {
        return Gson().toJson(subjects)
    }
}