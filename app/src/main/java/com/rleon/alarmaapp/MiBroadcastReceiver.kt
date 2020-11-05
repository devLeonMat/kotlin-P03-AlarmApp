package com.rleon.alarmaapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MiBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == ("com.gestoralarma")) {
            val msg = intent.extras // aqui recuperamo el mensaje
            Toast.makeText(context, msg.getString("Mensaje"), Toast.LENGTH_LONG).show()

            val notif = NotificacionPush()
            notif.notificacion(context!!, msg.getString("Mensaje"), 1)
        } else if (intent!!.action == ("android.intent.action.BOOT_COMPLETED")) {
            val gDatos = GuardarDatos(context!!)
            gDatos.programarAlarma()
        }
    }
}