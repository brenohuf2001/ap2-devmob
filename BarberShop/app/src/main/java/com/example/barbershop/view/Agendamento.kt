package com.example.barbershop.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String =""
    private var hora: String =""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_agendamento)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes : String
            if (dayOfMonth < 10){
                dia ="0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)

            }else{
                mes = (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }
        binding.timerPicker.setIs24HourView(true)

        binding.timerPicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val minuto: String
            if (minute < 10){
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }
            hora = "$hourOfDay:$minute"
        }
        binding.timerPicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {
            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3


            when{
                hora.isEmpty() ->{
                    mensagem(it,"Preencha o horario","#FFFFFF")
                }
                hora < "8:00" && hora > "19:00" ->{
                    mensagem(it,"estamos fechados!","#FFFFFF")

                }
                data.isEmpty() -> {
                    mensagem(it,"Preencha a data","#FFFFFF")

                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                    mensagem(it,"Pronto voce esta agendado","#FFD700")
                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                    mensagem(it,"Pronto voce esta agendado com ","#FFD700")
                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                    mensagem(it,"Pronto voce esta agendado com ","#FFD700")
                }else ->{
                mensagem(it,"Se eu tirar 10 o vasco vai ser campeão","#FFD700")

                }
            }
        }
    }
  private fun mensagem(view: View, mensagem: String, cor: String){
      val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
      snackbar.setBackgroundTint(Color.parseColor(cor))
      snackbar.setTextColor(Color.parseColor("#FFFFFF"))
    snackbar.show()
  }
}