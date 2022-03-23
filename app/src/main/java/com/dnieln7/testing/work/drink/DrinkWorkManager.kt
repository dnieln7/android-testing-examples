package com.dnieln7.testing.work.drink

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.*
import com.dnieln7.testing.work.drink.workers.DrinkReminderWorker

class DrinkWorkManager(
    context: Context,
    allowLowBattery: Boolean = false,
    networkType: NetworkType = NetworkType.NOT_REQUIRED
) {

    private val drinkRemainderTag = "DRINK_WATER_REMINDER"

    private var workManager: WorkManager = WorkManager.getInstance(context)
    private var constraints: Constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(!allowLowBattery)
        .setRequiredNetworkType(networkType)
        .build()

    val outputWorkInfos: LiveData<List<WorkInfo>>

    init {
        outputWorkInfos = workManager.getWorkInfosByTagLiveData(drinkRemainderTag)
    }

    fun start(name: String?) {
        val drinkData = workDataOf("NAME" to name)
        val drinkRequest = OneTimeWorkRequestBuilder<DrinkReminderWorker>()
            .addTag(drinkRemainderTag)
            .setInputData(drinkData)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(drinkRequest)
    }

    fun cancel() {
        workManager.cancelAllWorkByTag(drinkRemainderTag)
    }
}