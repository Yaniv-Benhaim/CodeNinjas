package tech.gamedev.codeninjas.data.retrofit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.ui.activities.MainActivity
import kotlin.random.Random

private const val CHANNEL_ID = "GLOBAL_NOTIFICATIONS"

class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this, MainActivity::class.java )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val smallRemoteView = RemoteViews(packageName, R.layout.notification_small)
        val bigRemoteView = RemoteViews(packageName, R.layout.notification_large)
        /*smallRemoteView.setTextViewText(R.id.notification_title, message.data["title"])
        smallRemoteView.setTextViewText(R.id.notification_message, message.data["message"])*/

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT )
        val notification =  NotificationCompat.Builder(this, CHANNEL_ID)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(smallRemoteView)
            .setCustomBigContentView(bigRemoteView)
            .setSmallIcon(R.drawable.assasin)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setColor(resources.getColor(R.color.orange))
            .setColorized(true)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "global notifications"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
                description = "Notifications are send to all users"
                enableLights(true)
                lightColor = Color.MAGENTA
            }

        notificationManager.createNotificationChannel(channel)

    }
}