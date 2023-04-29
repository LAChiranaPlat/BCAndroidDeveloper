package com.example.bcandroiddeveloper.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bcandroiddeveloper.data.model.Users

@Dao
interface DaoUser {

    @Insert
    suspend fun grabar(usuarios:Users):Long

    @Update
    suspend fun actualizar(usuarios:Users)

    @Delete
    suspend fun borrar(usuarios:Users)

    @Query("select * from usuarios")
    suspend fun listaUser():List<Users>

}