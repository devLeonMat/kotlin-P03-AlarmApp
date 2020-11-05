package com.rleon.alarmaapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*
import kotlin.math.min

class GuardarDatos(context: Context) {

    val context: Context? = context

    var sPreferences: SharedPreferences? = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    fun sharedPrefs(hora: Int, minutos: Int) {
        val editar = sPreferences!!.edit()
        editar.putInt("hora", hora)
        editar.putInt("minutos", minutos)
        editar.apply()
    }

    fun obtenerHora(): Int {
        return sPreferences!!.getInt("hora", 0)
    }

    fun obtenerMinutos(): Int {
        return sPreferences!!.getInt("minutos", 0)
    }


    fun programarAlarma() {

        val hora: Int = obtenerHora()
        val minutos: Int = obtenerMinutos()

        val calendario = Calendar.getInstance()
        calendario.set(Calendar.HOUR_OF_DAY, hora)
        calendario.set(Calendar.MINUTE, minutos)
        calendario.set(Calendar.SECOND, 0)

        val gestorAlarma = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, MiBroadcastReceiver::class.java)
        intent.putExtra("Mensaje", "Alarma Fiajadas a las: $hora:$minutos")
        intent.action = "com.gestoralarma"

        val iPending = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        gestorAlarma.setRepeating(AlarmManager.RTC_WAKEUP, calendario.timeInMillis, AlarmManager.INTERVAL_DAY, iPending)

    }

}