package com.example.bcandroiddeveloper.request.model

import com.google.gson.annotations.SerializedName

//ENVIO
data class login(
    @SerializedName("nick") var cuenta:String,
    @SerializedName("key") var password:String
)
