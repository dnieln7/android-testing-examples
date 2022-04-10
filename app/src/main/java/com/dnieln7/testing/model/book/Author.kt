package com.dnieln7.testing.model.book

import com.squareup.moshi.Json
import java.io.Serializable

data class Author(
    @Json(name = "birth_year")
    val birthYear: Int?,
    @Json(name = "death_year")
    val deathYear: Int?,
    @Json(name = "name")
    val name: String
):Serializable