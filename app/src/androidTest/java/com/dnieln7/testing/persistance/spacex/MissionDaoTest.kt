package com.dnieln7.testing.persistance.spacex

import com.dnieln7.testing.model.spacex.Mission
import com.dnieln7.testing.persistance.AppDatabase
import com.dnieln7.testing.persistance.spacex.dao.MissionDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class MissionDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Named("test_db")
    @Inject
    lateinit var appDatabase: AppDatabase

    private lateinit var missionDao: MissionDao

    @Before
    fun setUp() {
        hiltRule.inject()

        missionDao = appDatabase.missionDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insert() = runBlocking {
        val missions = listOf(
            Mission(
                missionId = "4aa8a2c2-8a5c-4a8f-bc21-10a2ebc12105",
                description = "First mission",
                missionName = "Test 1",
                twitter = "n/a",
                website = "n/a",
                wikipedia = "n/a"
            ),
        )

        missionDao.saveMissions(missions)
    }
}