package com.example.yhaa41.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yhaa41.R
import com.example.yhaa41.util.Talker
import com.example.yhaa41.room.Para
import com.example.yhaa41.room.ParaDatabase
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.ParaHelper
import com.google.gson.Gson
import kotlinx.coroutines.launch


class SingleTalking : BaseFragment() {
    var talkList = ArrayList<Talker>()
    var para: Para? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_talking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // pref = StoreData()
        talkList = ArrayList<Talker>()
        arguments?.let {
            para = SingleTalkingArgs.fromBundle(it).para
            var recognize = para!!.num
            Toast.makeText(context, "para num is -> $recognize", Toast.LENGTH_LONG).show()
            talkList = context?.let { it1 ->
                ParaHelper().creatTalkListFromTextFile(it1, recognize)
            }!!
        }
        val gson = Gson()
        // val tagNum = getCurrentFile()
        val jsonString = gson.toJson(talkList)
        para?.talkersString = jsonString
        launch {
            para?.let {
                context?.let { it1 ->
                    ParaDatabase(it1).getParaDao().updatePara(it)
                }
            }
        }

    }


}
