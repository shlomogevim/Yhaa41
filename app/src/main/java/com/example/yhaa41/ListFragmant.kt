package com.example.yhaa41

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_list_fragmant.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragmant : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_fragmant, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonList.setOnClickListener {
            val action=ListFragmantDirections.actionListFragmantToSingleTalking()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
