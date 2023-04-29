package com.example.bcandroiddeveloper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.bcandroiddeveloper.databinding.FragmentDashBoardBinding


class DashBoard : Fragment() {

    lateinit var views:FragmentDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        views= FragmentDashBoardBinding.inflate(inflater,container,false)

        views.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.addUser)
        }

        return views.root


    }

}