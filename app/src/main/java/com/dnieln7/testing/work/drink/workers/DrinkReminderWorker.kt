package com.dnieln7.testing.work.drink.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dnieln7.testing.utils.NotificationUtils

class DrinkReminderWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private val classTag = "${DrinkReminderWorker::class.simpleName}"

    private val notificationUtils by lazy { NotificationUtils(applicationContext) }

    override fun doWork(): Result {
        val name = inputData.getString("NAME")

        return if (name != null) {
            val notification = notificationUtils.drinkWater(name)

            notificationUtils.notify(notification)

            Result.success()
        } else {
            Log.e(classTag, "Name is null")

            Result.failure()
        }
    }
}