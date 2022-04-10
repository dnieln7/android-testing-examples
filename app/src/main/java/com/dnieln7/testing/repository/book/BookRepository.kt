package com.dnieln7.testing.repository.book

import androidx.lifecycle.LiveData
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.model.book.BookResponse
import com.dnieln7.testing.network.books.BooksApi
import com.dnieln7.testing.persistance.books.dao.BookDao
import com.dnieln7.testing.utils.ApiResponse
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val booksApi: BooksApi,
    private val bookDao: BookDao
) : IBookRepository {

    override suspend fun get(): ApiResponse {
        val response: Response<BookResponse>

        try {
            response = booksApi.get()
        } catch (e: Exception) {
            return ApiResponse.Error(0, e.localizedMessage ?: "Internal error")
        }

        return if (response.isSuccessful && response.body() != null) {
            bookDao.saveAll(response.body()?.books ?: emptyList())

            ApiResponse.Success
        } else {
            ApiResponse.Error(response.code(), response.errorBody()?.toString() ?: "Unknown error")
        }
    }

    override suspend fun saveAll(books: List<Book>) {
        bookDao.saveAll(books)
    }

    override suspend fun delete(book: Book) {
        bookDao.delete(book)
    }

    override fun observe(): LiveData<List<Book>> {
        return bookDao.observe()
    }
}