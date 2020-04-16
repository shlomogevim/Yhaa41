package com.example.yhaa41.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yhaa41.R
import com.example.yhaa41.room.Para
import com.example.yhaa41.room.ParaDatabase
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.ParaHelper
import kotlinx.android.synthetic.main.fragment_list_fragmant.*
import kotlinx.android.synthetic.main.helper_view_layout.*
import kotlinx.coroutines.launch


class ListFragmant : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_fragmant, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        paraRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        createParaListFromTheStart()
        // retriveParaList()
        //  deletAll()

    }


    private fun deletAll() {
        launch {
            context?.let {
                ParaDatabase(it).getParaDao().deleteAllParas()
            }
        }
    }

    private fun retriveParaList() {

        launch {
            context?.let {
                val paras = ParaDatabase(it).getParaDao().getAllParas()
                paraRV.adapter = ParaAdapter(paras)
            }
        }
    }

    private fun createParaListFromTheStart() {
        val paras = ParaHelper().setParaList()
        for (item in paras) {
            launch {
                savePara(item)
            }
        }
        paraRV.adapter = ParaAdapter(paras)
    }

    private suspend fun savePara(para: Para) {
        context?.let {
            ParaDatabase(it).getParaDao().addPara(para)
           // Toast.makeText(it, " note save", Toast.LENGTH_LONG).show()
        }

    }






    /* launch {
          context?.let {
              val paras=ParaDatabase(it).getParaDao().getAllParas()
              paraRV.adapter=ParaAdapter(paras)
          }
      }*/

    /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         Helper.Page.createConverList()
         val conversationList = Helper.Page.conversList
         listAtapter.updateConversationList(conversationList)

         paraRV.apply {
             layoutManager = LinearLayoutManager(context)
             adapter = listAtapter
         }

     }*/
}


/*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref = GetAndStoreData(this)
        pref.saveFirstTalk(true)

     //  val talkNum = -2
      /*      val talkNum = -1
        when (talkNum){
            -1->activateList()
            in 0..99->setSpesialTalk(talkNum)
            -2->activateWord()
        }*/
        activateList()
    }

    private fun activateWord() {
        val intent = Intent(this, OneTalking::class.java)
        intent.putExtra("TalkNum", 0)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.finish()
    }


    private fun activateList() {
        conversationList = ArrayList<Conversation>()
        Helper.Page.createConverList()
        conversationList = Helper.Page.conversList

        layoutManager = LinearLayoutManager(this)
        adapter = ConversationAdapter(this, conversationList!!)
        recyclerViewTalkingId.layoutManager = layoutManager
        recyclerViewTalkingId.adapter = adapter
        adapter!!.notifyDataSetChanged()

    }

    private fun setSpesialTalk(talkNum: Int) {
        val intent = Intent(this, OneTalking::class.java)
        intent.putExtra("TalkNum", talkNum)
        startActivity(intent)
    }*/
