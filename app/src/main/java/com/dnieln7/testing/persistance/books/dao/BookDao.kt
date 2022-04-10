package com.dnieln7.testing.persistance.books.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.model.cats.Cat

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(books: List<Book>)

    @Query("SELECT * FROM tb_books ORDER BY downloadCount DESC")
    suspend fun get(): List<Book>

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM tb_books ORDER BY downloadCount DESC")
    fun observe(): LiveData<List<Book>>
}