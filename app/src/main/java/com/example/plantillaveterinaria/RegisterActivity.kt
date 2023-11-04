package com.example.plantillaveterinaria

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.plantillaveterinaria.DB.API
import com.example.plantillaveterinaria.DB.Model
import com.example.plantillaveterinaria.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrarse()
        regresar()
    }
    fun registrarse(){
        binding.register.setOnClickListener {
            val data= Model(0,binding.rname.text.toString(), binding.rusuario.text.toString(),
                binding.rclave.text.toString()
            )
            API.db.postuser(data).enqueue(object : Callback<Model> {
                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                }

                override fun onFailure(call: Call<Model>, t: Throwable) {

                }
            })
            startActivity(Intent(this@RegisterActivity,IngresoActivity::class.java))
        }
    }
    fun regresar(){
        binding.back.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,IngresoActivity::class.java))
        }
    }
}