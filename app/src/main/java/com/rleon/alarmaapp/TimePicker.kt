package com.rleon.alarmaapp

import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.time_picker.*
import kotlinx.android.synthetic.main.time_picker.view.*

class TimePicker : DialogFragment(){ // extiende de DialogFragment

    // sobrescribimos onCreateView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // creamos el inflater
        val miVista = inflater!!.inflate(R.layout.time_picker, container, false)

        val btnHecho = miVista.btn_hecho // creamos una variable btn

        btnHecho.setOnClickListener{  // creamos el evento setOnClickListener
            val mActivity = activity as MainActivity // creamos esta variable para acceder asu metodo
            if(Build.VERSION.SDK_INT>=23){ // verificamos la Version de android si es mayor o igual al 23
                // si la version es Mayor o igual al 23 accedemos aqui
                mActivity.seteaHora(timePicker.hour, timePicker.minute) // acedemos al metodo del mainActivity y le pasamos los datos que pide
            }else{
                // si el metodo es menor al 23 o antiguas usamos esto con CurrentHour ....
                mActivity.seteaHora(timePicker.currentHour, timePicker.currentMinute)
            }
            this.dismiss() // usamos dismis para que se pueda mostrar
        }

        // retornamos la Vista
        return miVista
    }


}