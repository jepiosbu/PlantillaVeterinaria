package com.example.plantillaveterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.plantillaveterinaria.Adapter.RegistroAdapter
import com.example.plantillaveterinaria.DB.API
import com.example.plantillaveterinaria.DB.ModelRegistros
import com.example.plantillaveterinaria.IngresoActivity.Companion.key
import com.example.plantillaveterinaria.databinding.ActivityRegistrosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegistrosActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrosBinding
    lateinit var list: MutableList<ModelRegistros>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        llenar()
    }

    fun llenar(){
        API.db.getregitros(key.toInt()).enqueue(object : Callback<List<ModelRegistros>> {
            override fun onResponse(
                call: Call<List<ModelRegistros>>,
                response: Response<List<ModelRegistros>>
            ) {
                var data=response.body()
                binding.recycler.adapter = RegistroAdapter(data)
            }

            override fun onFailure(call: Call<List<ModelRegistros>>, t: Throwable) {
                Toast.makeText(this@RegistrosActivity, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}