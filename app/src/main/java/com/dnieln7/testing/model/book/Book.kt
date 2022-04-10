package com.dnieln7.testing.model.book

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "tb_books")
data class Book(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "authors")
    val authors: List<Author>,
    @Json(name = "subjects")
    val subjects: List<String>,
    @Json(name = "languages")
    val languages: List<String>,
    @Json(name = "bookshelves")
    val bookshelves: List<String>,
    @Json(name = "copyright")
    val copyright: Boolean?,
    @Json(name = "download_count")
    val downloadCount: Int,
    @Json(name = "media_type")
    val mediaType: String
):Serializable