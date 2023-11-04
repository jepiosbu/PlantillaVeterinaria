package com.example.plantillaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.plantillaveterinaria.DB.API
import com.example.plantillaveterinaria.DB.ModelMascota
import com.example.plantillaveterinaria.DB.ModeltMascota
import com.example.plantillaveterinaria.IngresoActivity.Companion.key
import com.example.plantillaveterinaria.databinding.ActivityMascotasBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotasActivity : AppCompatActivity() {
    lateinit var binding: ActivityMascotasBinding
    var tmascota:MutableList<String> = mutableListOf()
    var idtmascota:MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMascotasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idtmascota.clear()
        tmascota.clear()
        getmascota()
        save()
        back()
    }
    fun getmascota(){
        API.db.gettmascota().enqueue(object : Callback<List<ModeltMascota>> {
            override fun onResponse(
                call: Call<List<ModeltMascota>>,
                response: Response<List<ModeltMascota>>
            ) {
                var data=response.body()
                data!!.forEach {
                    idtmascota.add(it.id)
                    tmascota.add(it.Tname)
                }
                spinnermascota()
            }
            override fun onFailure(call: Call<List<ModeltMascota>>, t: Throwable) {

            }
        })
    }

    fun spinnermascota(){
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tmascota)
        binding.tmascota.adapter = adapter
    }

    fun save(){
        binding.save.setOnClickListener {
            val tid=idtmascota[binding.tmascota.selectedItemPosition]
            val data=ModelMascota(0,binding.rname.text.toString(),binding.redad.text.toString().toInt(),
                tid,key.toInt())
            API.db.postmascota(data).enqueue(object : Callback<ModelMascota> {
                override fun onResponse(
                    call: Call<ModelMascota>,
                    response: Response<ModelMascota>
                ) {

                }

                override fun onFailure(call: Call<ModelMascota>, t: Throwable) {
                    Toast.makeText(this@MascotasActivity, "Mascota Registrada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MascotasActivity,OptionActivity::class.java))
                }
            })
        }
    }
    fun back(){
        binding.back.setOnClickListener {
            startActivity(Intent(this@MascotasActivity,OptionActivity::class.java))
        }
    }
}