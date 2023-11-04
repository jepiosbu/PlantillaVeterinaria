package com.example.plantillaveterinaria.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantillaveterinaria.DB.ModelRegistros
import com.example.plantillaveterinaria.databinding.AdapterRegistrosBinding

class RegistroAdapter(val registro:List<ModelRegistros>?): RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = AdapterRegistrosBinding.inflate(LayoutInflater.
        from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bin(registro!![position])
    }

    override fun getItemCount(): Int {
        return registro!!.size
    }

    class ViewHolder(private val binding: AdapterRegistrosBinding):RecyclerView.ViewHolder(binding.root){
        fun bin(registro: ModelRegistros){
            binding.id.text = registro.id.toString()
            binding.mascota.text = registro.Mname
            binding.vacuna.text = registro.Vname
        }
    }

}