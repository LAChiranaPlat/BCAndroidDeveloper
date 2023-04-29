package com.example.bcandroiddeveloper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcandroiddeveloper.R
import com.example.bcandroiddeveloper.core.Uitilities
import com.example.bcandroiddeveloper.data.model.Users
import com.example.bcandroiddeveloper.data.repo.DBUsuario
import com.example.bcandroiddeveloper.databinding.ActivitySystemBinding
import com.example.bcandroiddeveloper.ui.adapter.myAdapter
import com.example.bcandroiddeveloper.ui.fragmentos.add
import kotlinx.coroutines.launch

class mySystem : AppCompatActivity(), myAdapter.InterfazDelete{

    lateinit var layout:ActivitySystemBinding
    var lista:ArrayList<Users> = ArrayList()

    //ADAPATADOR
    var adapter:myAdapter= myAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout= ActivitySystemBinding.inflate(layoutInflater)
        setContentView(layout.root)

        supportFragmentManager.setFragmentResultListener("data",this, FragmentResultListener{req,pack->
            if(pack.getInt("key").equals(1)){

                lifecycleScope.launch {
                    updateList(DBUsuario.list())
                }

            }
        })

        DBUsuario.ctx=this
        lifecycleScope.launch {

            updateList(DBUsuario.list())
            Log.i("result",lista.toString())

            adapter.myList.submitList(lista)

        }

        layout.apply {
            rvLista.layoutManager=LinearLayoutManager(this@mySystem)
            rvLista.adapter=adapter


            btnAdd.setOnClickListener {
                val agregar= add()

                agregar.show(supportFragmentManager.beginTransaction(),"agregar")


            }

            sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    var resultadoBusqueda=lista.filter { item->
                        item.nombres.lowercase().contains(newText!!.lowercase())
                    }

                    Log.i("result",resultadoBusqueda.toString())

                    adapter.myList.submitList(resultadoBusqueda as List<Users>)
                    adapter.notifyDataSetChanged()

                    return true
                }

            })
        }

    }

    fun updateList(list:List<Users>){
        lista.clear()
        lista.addAll(list)

        adapter.notifyDataSetChanged()
    }

    override fun delete(usuario: Users) {

        Uitilities.myMessage(this,"Atencion eliminado de usuarios","Esta seguro ${usuario.nombres}","Si, Eliminar",{
            lifecycleScope.launch {
                DBUsuario.delete(usuario)
                updateList(DBUsuario.list())
            }
        })

    }


}