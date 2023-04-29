package com.example.bcandroiddeveloper.core

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.example.bcandroiddeveloper.core.Uitilities.on
import com.google.android.material.textfield.TextInputLayout

object Uitilities {

    fun TextInputLayout.str():String{
        return this.editText?.text.toString()
    }

    fun TextInputLayout.cls(){
        this.editText?.text!!.clear()
    }

    fun TextInputLayout.vacio():Boolean{

        return this.editText?.text!!.isEmpty()
    }

    fun TextInputLayout.on(){

        this.editText?.requestFocus()

    }

    fun verifyEmpty(content:ConstraintLayout):Boolean{

        content.forEach {

            if(it is TextInputLayout)
                if(it.vacio())
                    return false
        }

        return true

    }

    fun myMessage(ctx:Context,title:String,message:String,btnStringOK:String,funOK:()->Unit){

        AlertDialog.Builder(ctx)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(btnStringOK){x,y->
                funOK()
            }
            .show()
    }

}