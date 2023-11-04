package com.example.plantillaveterinaria.DB

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path

interface MetodosApi {
    @GET("users")
    fun getuser(): Call<List<Model>>

    @GET("mascotas/{id}")
    fun getmascota(@Path("id") id: Int): Call<List<ModelMascota>>

    @POST("user/post")
    fun postuser(@Body user : Model): Call<Model>

    @GET("tmascotas")
    fun gettmascota(): Call<List<ModeltMascota>>

    @GET("vacunas")
    fun getvacuna(): Call<List<ModelVacuna>>

    @POST("mascota/post")
    fun postmascota(@Body mascota:ModelMascota): Call<ModelMascota>

    @POST("control/post")
    fun postcontrol(@Body vacuna:ModelControlVacuna): Call<ModelControlVacuna>

    @GET("registros/{id}")
    fun getregitros(@Path("id") id : Int): Call<List<ModelRegistros>>

}