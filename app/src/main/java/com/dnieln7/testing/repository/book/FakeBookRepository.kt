package com.dnieln7.testing.repository.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnieln7.testing.model.book.Author
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.model.book.BookResponse
import com.dnieln7.testing.utils.ApiResponse
import kotlinx.coroutines.delay
import retrofit2.Response

class FakeBookRepository : IBookRepository {

    private val _observableBooks = MutableLiveData<List<Book>>()

    override suspend fun get(): ApiResponse {
        delay(2000)

        val response = BookResponse(
            count = 1,
            next = null,
            previous = null,
            books = listOf(
                Book(
                    id = 84,
                    title = "Frankenstein; Or, The Modern Prometheus",
                    authors = listOf(
                        Author(
                            name = "Shelley, Mary Wollstonecraft",
                            birthYear = 1797,
                            deathYear = 1851
                        )
                    ),
                    subjects = listOf(
                        "Frankenstein's monster (Fictitious character) -- Fiction",
                        "Frankenstein, Victor (Fictitious character) -- Fiction",
                        "Gothic fiction",
                        "Horror tales",
                        "Monsters -- Fiction",
                        "Science fiction",
                        "Scientists -- Fiction"
                    ),
                    languages = listOf(
                        "en"
                    ),
                    bookshelves = listOf(
                        "Gothic Fiction",
                        "Movie Books",
                        "Precursors of Science Fiction",
                        "Science Fiction by Women"
                    ),
                    copyright = false,
                    mediaType = "Text",
                    downloadCount = 75355
                ),
                Book(
                    id = 1342,
                    title = "Pride and Prejudice",
                    authors = listOf(
                        Author(
                            name = "Austen, Jane",
                            birthYear = 1775,
                            deathYear = 1817
                        )
                    ),
                    subjects = listOf(
                        "Courtship -- Fiction",
                        "Domestic fiction",
                        "England -- Fiction",
                        "Love stories",
                        "Sisters -- Fiction",
                        "Social classes -- Fiction",
                        "Young women -- Fiction"
                    ),
                    languages = listOf(
                        "en"
                    ),
                    bookshelves = listOf(
                        "Best Books Ever Listings",
                        "Harvard Classics"
                    ),
                    copyright = false,
                    mediaType = "Text",
                    downloadCount = 60003
                )
            )
        )

        _observableBooks.value = response.books

        return ApiResponse.Success
    }

    override suspend fun saveAll(books: List<Book>) {
        _observableBooks.value = books
    }

    override suspend fun delete(book: Book) {
        val old = _observableBooks.value?.toMutableList() ?: mutableListOf()

        old.remove(book)

        _observableBooks.value = old
    }

    override fun observe(): LiveData<List<Book>> {
        return _observableBooks
    }
}