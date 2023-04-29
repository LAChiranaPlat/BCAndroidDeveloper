package com.example.bcandroiddeveloper.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bcandroiddeveloper.data.model.Users
import com.example.bcandroiddeveloper.databinding.TemplateListBinding

class myAdapter(val deleteListener:InterfazDelete):RecyclerView.Adapter<myAdapter.ContenedorVistas>() {

    var context:Context?=null
    class ContenedorVistas(var layout:TemplateListBinding):RecyclerView.ViewHolder(layout.root)

    interface InterfazDelete {
        fun delete(usuario:Users)
    }


    var callback=object : DiffUtil.ItemCallback<Users>(){
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.nombres == newItem.nombres
        }

    }

    var myList=AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorVistas {
        context=parent.context

        var layout=TemplateListBinding.inflate(LayoutInflater.from(context),parent,false)
        return ContenedorVistas(layout)
    }

    override fun getItemCount()=myList.currentList.size

    override fun onBindViewHolder(holder: ContenedorVistas, position: Int) {
        var currentItem=myList.currentList.get(position)

        holder.layout.apply {
            txtNombre.text=currentItem.nombres
            txtDecripcion.text=currentItem.apellidos

            imgDelete.setOnClickListener{
                deleteListener.delete(currentItem)
            }
/*
            imgDelete.setOnClickListener{
                modificar.modificar(currentItem)
            }
*/
        }
    }

}