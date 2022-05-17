package com.dnieln7.testing.work.drink

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.*
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.dnieln7.testing.work.drink.workers.DrinkReminderWorker
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class DrinkReminderWorkerTest {

    private val tag: String = "DRINK_REMINDER_TEST"
    private val allowLowBattery: Boolean = false
    private val networkType: NetworkType = NetworkType.NOT_REQUIRED

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun drinkReminder() {
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)

        val constraints: Constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(!allowLowBattery)
            .setRequiredNetworkType(networkType)
            .build()

        val drinkData = workDataOf("NAME" to "TEST")

        val drinkRequest = OneTimeWorkRequestBuilder<DrinkReminderWorker>()
            .addTag(tag)
            .setInputData(drinkData)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(context)

        workManager.enqueue(drinkRequest).result.get()

        testDriver?.setAllConstraintsMet(drinkRequest.id)

        val workInfo = workManager.getWorkInfoById(drinkRequest.id).get()

        assertEquals(workInfo.state, WorkInfo.State.SUCCEEDED)
    }

    @Test
    fun periodicDrinkReminder() {
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)

        val constraints: Constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(!allowLowBattery)
            .setRequiredNetworkType(networkType)
            .build()

        val drinkData = workDataOf("NAME" to "TEST")

        val drinkRequest = PeriodicWorkRequestBuilder<DrinkReminderWorker>(30, TimeUnit.MINUTES)
            .addTag(tag)
            .setInputData(drinkData)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(context)

        workManager.enqueue(drinkRequest).result.get()

        testDriver?.setInitialDelayMet(drinkRequest.id)
        testDriver?.setAllConstraintsMet(drinkRequest.id)
        testDriver?.setPeriodDelayMet(drinkRequest.id)
    }

    @Test
    fun listenable() {
        val drinkData = workDataOf("NAME" to "TEST")

        val worker = TestListenableWorkerBuilder<DrinkReminderWorker>(context)
            .setTags(listOf(tag))
            .setInputData(drinkData)
            .build()

        val result = worker.startWork().get()

        assertEquals(result, ListenableWorker.Result.success())
    }
}