package com.example.yhaa41.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation
import com.example.yhaa41.R
import com.example.yhaa41.action.AnimationInAction
import com.example.yhaa41.util.Talker
import com.example.yhaa41.room.Para
import com.example.yhaa41.room.ParaDatabase
import com.example.yhaa41.sentence.SentenceListFragmentDirections
import com.example.yhaa41.sentence.VideoFtagmentDirections
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.ParaHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sentence_list.*
import kotlinx.android.synthetic.main.fragment_single_talking.*
import kotlinx.android.synthetic.main.fragment_video_ftagment.*
import kotlinx.android.synthetic.main.god_layout.*
import kotlinx.coroutines.launch


class SingleTalking : BaseFragment(), View.OnClickListener {
    var talkList = ArrayList<Talker>()
    var para: Para? = null
    lateinit var animationInAction: AnimationInAction
    var recognizer=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback=
            requireActivity().onBackPressedDispatcher.addCallback(this){
                val action= SingleTalkingDirections.actionSingleTalkingToListFragmant()
                Navigation.findNavController(godSpeaking0).navigate(action)      }
        callback.isEnabled
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_talking, container, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> nextIt()
            R.id.fab1 -> previousIt()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            para = SingleTalkingArgs.fromBundle(it).para
            recognizer= para!!.num
        }
        when (recognizer){
            1->activateSentence()
            else-> activateList()
        }
    }


    private fun previousIt() {
        var currentPage = para?.currentPage!!
        currentPage--
        if (currentPage < 1) currentPage = 1
        para!!.currentPage = currentPage
        val talker = talkList[para!!.currentPage]
        animationInAction.executeTalker(talker)
    }

    private fun nextIt() {
        var max = talkList.size - 1
        var currentPage = para?.currentPage!!
        currentPage++
        if (currentPage > max) currentPage = 1
        para!!.currentPage = currentPage
        val talker = talkList[para!!.currentPage]
        animationInAction.executeTalker(talker)
    }

    private fun activateList() {
        talkList = context?.let { it1 ->
            ParaHelper().creatTalkListFromTextFile(it1, recognizer)
        }!!
        val gson = Gson()
        val jsonString = gson.toJson(talkList)
        para?.talkersString = jsonString
        launch {
            para?.let {
                context?.let { it1 ->ParaDatabase(it1).getParaDao().updatePara(it)}
            }
        }
        animateList()
    }

    private fun animateList() {
        animationInAction = context?.let { AnimationInAction(it) }!!
        val talker = talkList[para!!.currentPage]
        animationInAction.executeTalker(talker)
        fab.setOnClickListener(this)
        fab1.setOnClickListener(this)
    }

    private fun activateSentence() {


    }




   /* private fun getPara() {
        arguments?.let {
            para = SingleTalkingArgs.fromBundle(it).para
            var recognize = para!!.num
            talkList = context?.let { it1 ->
                ParaHelper().creatTalkListFromTextFile(it1, recognize)
            }!!
        }
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
    }*/


}
