package com.dnieln7.testing.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dnieln7.testing.R

class NotificationUtils(context: Context) : ContextWrapper(context) {

    private val default = "General Messages"
    private val defaultID = "com.dnieln7.testing.GENERAL"

    init {
        createGeneralChannel()
    }

    private fun getManager(): NotificationManager {
        return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun createGeneralChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val generalChannel = NotificationChannel(
                defaultID,
                default,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            generalChannel.enableLights(true)
            generalChannel.enableVibration(true)
            generalChannel.importance = NotificationManager.IMPORTANCE_DEFAULT
            generalChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            getManager().createNotificationChannel(generalChannel)
        }
    }

    fun drinkWater(name: String): Notification {
        val notification: Notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder = Notification.Builder(this, defaultID)

            notification = builder
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.time_to_drink, name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

        } else {
            val builder = NotificationCompat.Builder(this, defaultID)

            notification = builder
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.time_to_drink, name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()
        }

        return notification
    }

    fun notify(notification: Notification) {
        getManager().notify(0, notification)
    }
}