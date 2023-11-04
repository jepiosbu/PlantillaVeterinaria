package com.example.plantillaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantillaveterinaria.databinding.ActivityOptionBinding

class OptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vacunas()
        mascotas()
        registros()
        logout()
    }
    fun mascotas(){
        binding.rmascotas.setOnClickListener {
            startActivity(Intent(this@OptionActivity,MascotasActivity::class.java))
        }
    }
    fun vacunas(){
        binding.rvacunas.setOnClickListener {
            startActivity(Intent(this@OptionActivity,VacunasActivity::class.java))
        }
    }
    fun registros(){
        binding.verregistros.setOnClickListener {
            startActivity(Intent(this@OptionActivity,RegistrosActivity::class.java))
        }
    }
    fun logout(){
        binding.logout.setOnClickListener {
            startActivity(Intent(this@OptionActivity,IngresoActivity::class.java))
        }
    }
}