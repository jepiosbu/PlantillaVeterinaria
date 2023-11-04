package com.example.plantillaveterinaria.DB


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    val construccion=Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/").addConverterFactory(
        GsonConverterFactory.create()).build()
    val db= construccion.create(MetodosApi::class.java)
}