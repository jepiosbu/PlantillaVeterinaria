package com.example.plantillaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.plantillaveterinaria.DB.API
import com.example.plantillaveterinaria.DB.ModelControlVacuna
import com.example.plantillaveterinaria.DB.ModelMascota
import com.example.plantillaveterinaria.DB.ModelVacuna
import com.example.plantillaveterinaria.IngresoActivity.Companion.key
import com.example.plantillaveterinaria.databinding.ActivityVacunasBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VacunasActivity : AppCompatActivity() {
    lateinit var binding: ActivityVacunasBinding
    var idmascota: MutableList<Int> = mutableListOf()
    var namemascota: MutableList<String> = mutableListOf()
    var idvacuna: MutableList<Int> = mutableListOf()
    var namevacuna: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVacunasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idmascota.clear()
        namemascota.clear()
        idvacuna.clear()
        namevacuna.clear()
        dtMascota()
        dtVacunas()
        back()
        save()
    }

     fun dtMascota(){

         API.db.getmascota(key.toInt()).enqueue(object : Callback<List<ModelMascota>> {
             override fun onResponse(
                 call: Call<List<ModelMascota>>,
                 response: Response<List<ModelMascota>>
             ) {
                 var data = response.body()
                 data!!.forEach {
                     idmascota.add(it.id)
                     namemascota.add(it.Mname)
                 }
                 spinnermascota()
             }

             override fun onFailure(call: Call<List<ModelMascota>>, t: Throwable) {
                 TODO("Not yet implemented")
             }
         })
     }
    fun spinnermascota(){
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namemascota)
        binding.mascotaspi.adapter = adapter
    }

    fun dtVacunas(){
        API.db.getvacuna().enqueue(object : Callback<List<ModelVacuna>> {
            override fun onResponse(
                call: Call<List<ModelVacuna>>,
                response: Response<List<ModelVacuna>>
            ) {
                var data = response.body()
                data!!.forEach {
                    idvacuna.add(it.id)
                    namevacuna.add(it.Vname)
                }
                spinnervacuna()
            }

            override fun onFailure(call: Call<List<ModelVacuna>>, t: Throwable) {

            }
        })
    }
    fun spinnervacuna(){
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namevacuna)
        binding.vacunaspi.adapter = adapter
    }
    fun back(){
        binding.back.setOnClickListener {
            startActivity(Intent(this@VacunasActivity ,OptionActivity::class.java))
        }
    }
    fun save(){
        binding.save.setOnClickListener {
            var refidmascota=idmascota[binding.mascotaspi.selectedItemPosition]
            var refidvacuna=idvacuna[binding.vacunaspi.selectedItemPosition]
            var vacunacion=ModelControlVacuna(0,refidmascota,refidvacuna)
            API.db.postcontrol(vacunacion).enqueue(object : Callback<ModelControlVacuna> {
                override fun onResponse(
                    call: Call<ModelControlVacuna>,
                    response: Response<ModelControlVacuna>
                ) {

                }

                override fun onFailure(call: Call<ModelControlVacuna>, t: Throwable) {
                    Toast.makeText(this@VacunasActivity, "Vacuna Exitosa", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@VacunasActivity,OptionActivity::class.java))
                }
            })
        }
    }
}