package com.example.bcandroiddeveloper.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Users (
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "name") var nombres:String,
    @ColumnInfo(name = "lname") var apellidos:String,
    var sexo:String
    )
