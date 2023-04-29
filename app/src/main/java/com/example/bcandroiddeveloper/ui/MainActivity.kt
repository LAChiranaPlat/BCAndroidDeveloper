package com.example.bcandroiddeveloper.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.example.bcandroiddeveloper.R
import com.example.bcandroiddeveloper.core.Uitilities.cls
import com.example.bcandroiddeveloper.core.Uitilities.myMessage
import com.example.bcandroiddeveloper.core.Uitilities.on
import com.example.bcandroiddeveloper.core.Uitilities.str
import com.example.bcandroiddeveloper.core.Uitilities.verifyEmpty
import com.example.bcandroiddeveloper.data.model.Users
import com.example.bcandroiddeveloper.data.repo.DBUsuario
import com.example.bcandroiddeveloper.databinding.ActivityMainBinding
import com.example.bcandroiddeveloper.request.Servicess.myServices
import com.example.bcandroiddeveloper.request.model.login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var layout:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout=ActivityMainBinding.inflate(layoutInflater)

        setContentView(layout.root)

       layout.apply {


            btnIngresar.onFocusChangeListener=object :OnFocusChangeListener{

                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if(hasFocus)
                    {
                        val imm=this@MainActivity.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v?.windowToken,0)
                    }
                }

            }

            btnIngresar.setOnClickListener {

                if(verifyEmpty(contentLogin)==false){
                    //Toast.makeText(this@MainActivity,"Debe Ingresar sus Accesos",Toast.LENGTH_LONG).show()

                   myMessage(
                       this@MainActivity,
                       getString(R.string.tituloLoginControlAcceso),
                       getString(R.string.mensajeLoginControlAcceso),
                       getString(R.string.btnOKControlAcceso),
                       {
                           tilUser.on()
                       }
                   )

                    return@setOnClickListener
                }

                loading.visibility=View.VISIBLE

                lifecycleScope.launch {
                    val result= withContext(Dispatchers.IO){

                        try{
                           myServices().logear(login(tilUser.str(),tilPassword.str()))
                        }catch(e:java.lang.Exception){
                            Log.i("result",e.message.toString())
                            null
                        }

                    }

                    loading.visibility=View.GONE

                    if(result!=null){
                        Log.i("result",result.toString())

                        var obj=result.body()

                        Log.i("result",obj.toString())

                        if(obj?.status!!.equals(1)) {
                            startActivity(Intent(this@MainActivity, mySystem::class.java))
                        }else{
                            myMessage(this@MainActivity,"Error de Identificacion",obj.message.toString(),"Ok",{
                                tilUser.cls()
                                tilPassword.cls()
                                tilUser.on()
                            })
                        }

                    }
                }
                //

            }

        }

    }

}