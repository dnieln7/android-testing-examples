package com.dnieln7.testing.persistance.cats

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dnieln7.testing.model.cats.Cat
import com.dnieln7.testing.persistance.AppDatabase
import com.dnieln7.testing.persistance.cats.dao.CatDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var catDao: CatDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        catDao = appDatabase.catDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insert() = runBlocking {
        val cat = Cat(
            name = "Bingus",
            description = "Egyptian hairless cat",
            dogFriendly = false,
            strangerFriendly = true,
            childFriendly = true
        )

        catDao.insert(cat)

        val cats: List<Cat> = catDao.getCats().first()
        val bingus = cats.firstOrNull()

        assertNotNull(bingus)
        assertEquals(bingus?.name, "Bingus")
    }
}