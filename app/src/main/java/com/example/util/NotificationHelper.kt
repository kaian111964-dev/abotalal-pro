package com.example.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R

object NotificationHelper {

    private const val CHANNEL_ID = "abotalal_notifications_channel"
    private const val CHANNEL_NAME = "إشعارات تطبيق ابو طلال"
    private const val CHANNEL_DESC = "تنبيهات العروض وخدمات البرمجة والتسويق والدعم الفني"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESC
                enableVibration(true)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        notificationId: Int = System.currentTimeMillis().toInt()
    ) {
        // Check permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
        }

        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setColor(0xFF0D57CF.toInt())

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, builder.build())
    }

    fun sendWelcomeNotification(context: Context) {
        showNotification(
            context = context,
            title = "مرحباً بك في تطبيق ابو طلال 🌟",
            message = "تم تفعيل الإشعارات بنجاح! ستصلك أحدث العروض والخدمات الرقمية وأخبار التحديثات فوراً.",
            notificationId = 1001
        )
    }

    fun sendBookingNotification(context: Context, serviceTitle: String) {
        showNotification(
            context = context,
            title = "تم تأكيد طلب الحجز بنجاح ✅",
            message = "طلبك لخدمة ($serviceTitle) قيد المعالجة الآن. سيتواصل معك فريق ابو طلال عبر واتساب.",
            notificationId = 1002
        )
    }

    fun sendDemoAlertNotification(context: Context) {
        showNotification(
            context = context,
            title = "عرض حصري جديد من ابو طلال 🔥",
            message = "خصم 25% على باقات البرمجة وتطوير التطبيقات والتسويق الرقمي لفترة محدودة!",
            notificationId = (1003..9999).random()
        )
    }
}
