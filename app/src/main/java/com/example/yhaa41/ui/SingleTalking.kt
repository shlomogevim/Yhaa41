package com.example.yhaa41.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.yhaa41.util.GetAndStoreData
import com.example.yhaa41.R
import com.example.yhaa41.action.AnimationInAction
import com.example.yhaa41.action.AnimationInActionFragment
import com.example.yhaa41.util.Talker
import com.example.yhaa41.room.Para
import com.example.yhaa41.room.ParaDatabase
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.ParaHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_single_talking.*
import kotlinx.android.synthetic.main.god_layout.*
import kotlinx.android.synthetic.main.helper_view_layout.*
import kotlinx.coroutines.launch


class SingleTalking : BaseFragment(), View.OnClickListener {
    var talkList = ArrayList<Talker>()
    var para: Para? = null
    lateinit var animationInAction: AnimationInAction

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
        val talker = talkList[para!!.currentPage]
        animationInAction.executeTalker(talker)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab.setOnClickListener(this)
        fab1.setOnClickListener(this)
        getPara()
        animationInAction = context?.let { AnimationInAction(it) }!!
        val talker = talkList[para!!.currentPage]
        animationInAction.executeTalker(talker)
    }

    private fun previousIt() {
        var currentPage = para?.currentPage!!
        currentPage--
        if (currentPage < 1) currentPage = 1
        para!!.currentPage = currentPage
    }

    private fun nextIt() {
        var max = talkList.size - 1
        var currentPage = para?.currentPage!!
        currentPage++
        if (currentPage > max) currentPage = 1
        para!!.currentPage = currentPage
    }


    private fun getPara() {
        arguments?.let {
            para = SingleTalkingArgs.fromBundle(it).para
            var recognize = para!!.num
            // Toast.makeText(context, "para num is -> $recognize", Toast.LENGTH_LONG).show()
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
    }




}
