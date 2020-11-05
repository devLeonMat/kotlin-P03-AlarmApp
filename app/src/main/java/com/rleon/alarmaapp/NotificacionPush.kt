package com.rleon.alarmaapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat

class NotificacionPush {
    val NOTIFICACION = "Peticion Nueva"
    val NOTIFi_CHANNEL_ID = "miChannelID"

    fun notificacion(context: Context, mensaje: String, numero: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val gestorNotificacion = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifChannel = NotificationChannel(NOTIFi_CHANNEL_ID, "Mi Notificacion", NotificationManager.IMPORTANCE_HIGH)
            notifChannel.description = "Descripcion "
            notifChannel.lightColor = Color.BLUE
            notifChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notifChannel.enableLights(true)
            notifChannel.enableVibration(true)
            gestorNotificacion.createNotificationChannel(notifChannel)

        }


        val builder = NotificationCompat.Builder(context, NOTIFi_CHANNEL_ID)
                .setContentTitle("Simple mortal Alarma")
                .setContentText(mensaje)
                .setColorized(true)
                .setColor(Color.RED)
                .setNumber(numero)
                .setSmallIcon(R.drawable.icono)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT in 19..25) {
            gestorNotificacion.notify(NOTIFICACION, 0, builder.build())
        } else {
            gestorNotificacion.notify(NOTIFICACION.hashCode(), builder.build())
        }


    }
}