package com.example.moviesapp.screen.mainScreen

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.widget.Toast

object Main {
    fun checkNetworkConnectivity(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        val isConnect = capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        if(!isConnect)
            Toast.makeText(context, "Không có kết nối mạng", Toast.LENGTH_SHORT).show()

        return isConnect
    }
}