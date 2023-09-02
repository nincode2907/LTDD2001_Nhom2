package com.example.moviesapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.moviesapp.presentation.signIn.GoogleAuthUiClient
import com.example.moviesapp.screen.mainScreen.Main.checkNetworkConnectivity
import com.example.petadoption.bottomnav.BottomBarAnimationApp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var pressedTime: Long = 0
    private val CHANNEL_ID = "movies_app_29"
    private val notificationID = 2907

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext),
        )
    }
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation",
        "UnsafeOptInUsageError", "MissingPermission"
    )
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isConnected by remember { mutableStateOf(checkNetworkConnectivity(this)) }
            val notificationPermission = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

            if (!isConnected) {
                Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show()
            }

            createNotificationChannel()
            if(!notificationPermission.status.isGranted) {
                LaunchedEffect(Unit) {
                    notificationPermission.launchPermissionRequest()
                }
            } else
                sendNotification(this)

            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.navigationBars())
                hide(WindowInsetsCompat.Type.statusBars())
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

            BottomBarAnimationApp(googleAuthUiClient)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "Nhấn trở về một lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = "Thông báo mới"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(context: Context) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Tạo thông báo
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications)
            .setContentTitle("Ngày mới xuất hiện phim hay")
            .setContentText("Đã có phim mới xuất hiện. Thưởng thức ngay thôi nào..")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationID, builder.build())
        }
    }
}

