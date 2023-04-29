package com.example.bcandroiddeveloper.ui.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.bcandroiddeveloper.R
import com.example.bcandroiddeveloper.core.Uitilities.cls
import com.example.bcandroiddeveloper.core.Uitilities.on
import com.example.bcandroiddeveloper.core.Uitilities.str
import com.example.bcandroiddeveloper.data.model.Users
import com.example.bcandroiddeveloper.data.repo.DBUsuario
import com.example.bcandroiddeveloper.databinding.FragmentAddBinding
import kotlinx.coroutines.launch


class add : DialogFragment() {

    lateinit var layout:FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout= FragmentAddBinding.inflate(inflater,container,false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout.apply {
            btnGrabar.setOnClickListener {
                DBUsuario.ctx=requireContext()
                lifecycleScope.launch {

                    DBUsuario.save(Users(0,tilNombres.str(),tilApellidos.str(),"m"))

                    var pack=Bundle()
                    pack.putInt("key",1)
                    parentFragmentManager.setFragmentResult("data",pack)

                    Toast.makeText(requireContext(),"Usuario Registrado Correctamente",Toast.LENGTH_LONG).show()
                    tilNombres.cls()
                    tilApellidos.cls()

                    tilNombres.on()

                }
            }

            btnClose.setOnClickListener {
                dismiss()
            }
        }
    }


}