package com.example.yhaa41

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SingleTalking : Fragment() {
    //lateinit var pref: GetAndStoreData
    lateinit var pref:StoreData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_talking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val pref=this.activity.getSharedPreferences()


        pref = StoreData()

        arguments?.let {
           val numTalking=SingleTalkingArgs.fromBundle(it).posi
           pref.createListZero(numTalking)
           val list=pref.getTalkingList(1)
        }
    }

}
