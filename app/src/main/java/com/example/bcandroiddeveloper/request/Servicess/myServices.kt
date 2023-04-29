package com.example.bcandroiddeveloper.request.Servicess

import com.example.bcandroiddeveloper.request.Router.Router
import com.example.bcandroiddeveloper.request.core.objRetrofit
import com.example.bcandroiddeveloper.request.model.login
import com.example.bcandroiddeveloper.request.model.resultRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class myServices {

    private val myRetrofit = objRetrofit.getRerofit()

    suspend fun logear(login:login): Response<resultRequest> {

        return withContext(Dispatchers.IO){
            myRetrofit.create(Router::class.java).loginUser(login)
        }

    }

}