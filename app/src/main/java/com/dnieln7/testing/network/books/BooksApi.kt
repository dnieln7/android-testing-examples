package com.dnieln7.testing.network.books

import com.dnieln7.testing.model.book.BookResponse
import com.dnieln7.testing.model.spacex.Mission
import retrofit2.Response
import retrofit2.http.GET

interface BooksApi {

    @GET("books")
    suspend fun get(): Response<BookResponse>
}