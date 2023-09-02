package com.example.moviesapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val now = Calendar.getInstance()

        val scheduledTime = Calendar.getInstance()
        scheduledTime.set(Calendar.HOUR_OF_DAY, 5)
        scheduledTime.set(Calendar.MINUTE, 14)
        scheduledTime.set(Calendar.SECOND, 0)

        if (now.after(scheduledTime)) {
            scheduledTime.add(Calendar.DAY_OF_MONTH, 1)
        }

        val delay = scheduledTime.timeInMillis - now.timeInMillis
        val timeCu = System.currentTimeMillis() + 10000

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInitialDelay(10000, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}