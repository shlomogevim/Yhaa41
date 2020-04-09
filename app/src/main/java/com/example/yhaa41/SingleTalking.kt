package com.example.yhaa41

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_single_talking.*


class SingleTalking : Fragment() {

   var convrs: Conversation? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_talking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonSingle.setOnClickListener {
           /* val action=SingleTalkingDirections.actionSingleTalkingToListFragmant()
            Navigation.findNavController(it).navigate(action)*/
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            convrs=SingleTalkingArgs.fromBundle(it).convers
        }
    }

}