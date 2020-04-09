package com.example.yhaa41

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_fragmant.*


class ListFragmant : Fragment() {
   // private var adapter: ConversationAdapter? = null
   // private var conversationList: ArrayList<Conversation>? = null
   // private var layoutManager: RecyclerView.LayoutManager? = null
    private val listAtapter=ConversationAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_fragmant, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //  val talkNum = -2
        /*      val talkNum = -1
          when (talkNum){
              -1->activateList()
              in 0..99->setSpesialTalk(talkNum)
              -2->activateWord()
          }*/
       // activateList()
    }

       /* buttonList.setOnClickListener {
            val action=ListFragmantDirections.actionListFragmantToSingleTalking()
            Navigation.findNavController(it).navigate(action)
        }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Helper.Page.createConverList()
        val conversationList = Helper.Page.conversList
        listAtapter.updateConversationList(conversationList)

        conversationRV.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=listAtapter
        }


         /* layoutManager = LinearLayoutManager(context)
              //  adapter = ConversationAdapter(context, conversationList!!)
          adapter = ConversationAdapter( conversationList!!)
          recyclerViewTalkingId.layoutManager = layoutManager
          recyclerViewTalkingId.adapter = adapter
          adapter!!.notifyDataSetChanged()*/

    }




       private fun activateList() {
          /* conversationList = ArrayList<Conversation>()
           Helper.Page.createConverList()
           conversationList = Helper.Page.conversList


           layoutManager = LinearLayoutManager(this)
           adapter = ConversationAdapter(this, conversationList!!)
           recyclerViewTalkingId.layoutManager = layoutManager
           recyclerViewTalkingId.adapter = adapter
           adapter!!.notifyDataSetChanged()*/

       }
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
