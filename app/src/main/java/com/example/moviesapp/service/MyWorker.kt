package com.example.moviesapp.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R


class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val CHANNEL_ID = "movies_app_20"
    private val notificationID = 100

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        val notification = createNotification()

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationID, notification.build())
        }

        return Result.success()
    }

    private fun createNotification(): NotificationCompat.Builder {
        val notificationIntent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        createNotificationChannel()

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications)
            .setContentTitle("Ngày mới xuất hiện phim hay")
            .setContentText("Đã có phim mới xuất hiện. Thưởng thức ngay thôi nào..")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = applicationContext.getString(R.string.app_name)
            val descriptionText = "Thông báo mới"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}