package com.example.bcandroiddeveloper.request.Router

import com.example.bcandroiddeveloper.request.model.login
import com.example.bcandroiddeveloper.request.model.resultRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Router {

    @POST("myAPI/proData.php")
    suspend fun loginUser(@Body login:login):Response<resultRequest>
/*
    @GET("myAPI/proData.php?nombre={name}&dni={dni}")
    suspend fun sendUser(@Query("name") name:String,@Query("dni") doc:String)

    @GET("myAPI/{USER}")
    suspend fun sendData(@Path("USER") ruta:String)
*/

}