package com.example.bcandroiddeveloper.request.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object objRetrofit {

    fun getRerofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://lachiranaplat.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}