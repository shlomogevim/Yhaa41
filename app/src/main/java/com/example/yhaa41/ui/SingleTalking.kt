package com.example.yhaa41.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.yhaa41.util.GetAndStoreData
import com.example.yhaa41.R
import com.example.yhaa41.action.AnimationInAction
import com.example.yhaa41.util.Talker
import com.example.yhaa41.room.Para
import com.example.yhaa41.room.ParaDatabase
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.ParaHelper
import com.google.gson.Gson
import kotlinx.coroutines.launch


class SingleTalking : BaseFragment() {
    lateinit var pref: GetAndStoreData
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
        talkList = ArrayList<Talker>()
       //pref=GetAndStoreData(context)
        arguments?.let {
            para = SingleTalkingArgs.fromBundle(it).para
            var recognize = para!!.num
           // pref.saveRecognizer(recognize)
            Toast.makeText(context, "para num is -> $recognize", Toast.LENGTH_LONG).show()
            talkList = context?.let { it1 ->
                ParaHelper().creatTalkListFromTextFile(it1, recognize)
            }!!
        }


        pref.saveTalkingList(talkList)
        val gson = Gson()
        val jsonString = gson.toJson(talkList)
        para?.talkersString = jsonString
        launch {
            para?.let {
                context?.let { it1 ->
                    ParaDatabase(it1).getParaDao().updatePara(it)
                }
            }
        }
        val animateInAction= context?.let { AnimationInAction(it) }
        val talker=talkList[para?.currentPage!!]
        animateInAction?.executeTalker(talker)
    }

    /*val action=
             SingleTalkingDirections.actionSingleTalkingToAnimationInActionFragment()
         action.talker=talkList[para?.currentPage!!]
         Navigation.findNavController(godSpeaking0).navigate(action)*/

        /*val action=ListFragmantDirections.actionListFragmantToSingleTalking()
        action.para=paras[position]
        Navigation.findNavController(it).navigate(action)*/


}
