package com.dnieln7.testing.repository.book

import androidx.lifecycle.LiveData
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.network.books.BooksApi
import com.dnieln7.testing.persistance.books.dao.BookDao
import com.dnieln7.testing.utils.DataResponse
import com.dnieln7.testing.utils.DataSource
import kotlinx.coroutines.*
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val booksApi: BooksApi,
    private val bookDao: BookDao
) : IBookRepository {

    override suspend fun get(): DataResponse<List<Book>> = coroutineScope {
        val dataResponse = try {
            val response = booksApi.get()

            if (response.isSuccessful) {
                DataResponse(data = response.body()?.books ?: emptyList(), source = DataSource.API)
            } else {
                val local = bookDao.get()

                DataResponse(data = local, source = DataSource.DATABASE)
            }
        } catch (e: Exception) {
            val local = bookDao.get()

            DataResponse(data = local, source = DataSource.DATABASE, error = e.localizedMessage)
        }

        return@coroutineScope dataResponse
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