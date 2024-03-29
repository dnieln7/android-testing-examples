package com.dnieln7.testing.repository.book

import androidx.lifecycle.LiveData
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.utils.ApiResponse
import com.dnieln7.testing.utils.DataResponse

interface IBookRepository {

    suspend fun get(): DataResponse<List<Book>>

    suspend fun saveAll(books: List<Book>)

    suspend fun delete(book: Book)

    fun observe(): LiveData<List<Book>>
}