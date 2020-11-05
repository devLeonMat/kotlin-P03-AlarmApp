package com.rleon.alarmaapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gDatos = GuardarDatos(applicationContext)
        tv_Hora.text = gDatos.obtenerHora().toString()+":"+gDatos.obtenerMinutos().toString()

        btn_ok.setOnClickListener { // creamos el evento al btn
            val tiempo = TimePicker() // creamos una variable de tipo TimePicker
            val fragmentManager = fragmentManager // para poder crear un multipanel

            tiempo.show(fragmentManager, "Selecciona una Hora") // mostramos el time picker
        }
    }

    // creamos la funcion para programar la hora de alarma que recibe hora y minutos
    fun seteaHora(hora: Int, minutos: Int) {
        tv_Hora.text = hora.toString() + ":" + minutos.toString() // mandamos la Hora a la vista

        val guardarDatos = GuardarDatos(applicationContext)
        guardarDatos.programarAlarma()
        guardarDatos.sharedPrefs(hora, minutos)
    }


}
