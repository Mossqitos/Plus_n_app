package com.example.plus_n

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.plus_n.Start_page.GameActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class homepage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_homepage, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnStart).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.firstnav)
        }
    }


}