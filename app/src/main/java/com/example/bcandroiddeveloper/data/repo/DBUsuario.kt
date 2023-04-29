package com.example.bcandroiddeveloper.data.repo

import android.content.Context
import com.example.bcandroiddeveloper.data.db.DBUsers
import com.example.bcandroiddeveloper.data.model.Users

object DBUsuario {

    var ctx:Context?=null

    suspend fun save(user:Users){
        DBUsers.getInstance(ctx!!.applicationContext).dao.grabar(user)
    }

    suspend fun list():List<Users>{
        return DBUsers.getInstance(ctx!!.applicationContext).dao.listaUser()
    }

    suspend fun delete(user:Users){
        DBUsers.getInstance(ctx!!.applicationContext).dao.borrar(user)
    }
}