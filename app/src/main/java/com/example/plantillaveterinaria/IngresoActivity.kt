package com.example.plantillaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.plantillaveterinaria.DB.API
import com.example.plantillaveterinaria.DB.Model
import com.example.plantillaveterinaria.databinding.ActivityIngresoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngresoActivity : AppCompatActivity() {
    companion object{
        lateinit var key: String
    }
    var usuarios:MutableList<String> = mutableListOf()
    var claves:MutableList<String> = mutableListOf()
    var id:MutableList<Int> = mutableListOf()
    lateinit var binding: ActivityIngresoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarios.clear()
        claves.clear()
        id.clear()
        getusuairos()
        loggin()
        register()
    }
    fun getusuairos(){
        API.db.getuser().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                var data= response.body()
                data!!.forEach{
                    id.add(it.id)
                    usuarios.add(it.correo)
                    claves.add(it.password)
                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Log.e("error",t.message.toString())
            }
        })
    }
    fun loggin(){
        binding.validate.setOnClickListener {
            if(binding.usuario.text.toString() in usuarios && binding.clave.text.toString() in claves){
                key=id[usuarios.indexOf(binding.usuario.text.toString())].toString()
                startActivity(Intent(this,OptionActivity::class.java))
            }
            else if(binding.usuario.text.toString() in usuarios){
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Usuario No Registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun register(){
        binding.register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}
