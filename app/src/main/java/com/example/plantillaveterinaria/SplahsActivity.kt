package com.example.plantillaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantillaveterinaria.databinding.ActivitySplahsBinding

class SplahsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplahsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplahsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Next()
    }
    fun Next(){
        binding.logosplahs.animate().scaleX(1.7f).scaleY(1.7f).setDuration(4000).withEndAction {
            startActivity(Intent(this,IngresoActivity::class.java))
        }
    }
}