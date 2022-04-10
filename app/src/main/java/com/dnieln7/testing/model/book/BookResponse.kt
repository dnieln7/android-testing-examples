package com.dnieln7.testing.model.book

import com.squareup.moshi.Json

data class BookResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val books: List<Book>
)