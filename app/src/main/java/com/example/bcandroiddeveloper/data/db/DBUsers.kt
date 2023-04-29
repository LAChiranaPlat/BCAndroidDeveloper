package com.example.bcandroiddeveloper.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bcandroiddeveloper.data.dao.DaoUser
import com.example.bcandroiddeveloper.data.model.Users

@Database(
    entities = [Users::class,],
    version = 1,
    exportSchema = false
)

abstract class DBUsers:RoomDatabase() {

    abstract val dao:DaoUser

    companion object{

        @Volatile
        private var INSTANCIA:DBUsers?=null

        fun getInstance(ctx:Context):DBUsers{

            synchronized(this){
                var instance= INSTANCIA

                if(instance==null){

                    instance= Room.databaseBuilder(
                        ctx.applicationContext,
                        DBUsers::class.java,
                        "contactos"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCIA=instance
                }

                return instance
            }

        }
    }

}