package com.example.yhaa41

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_row.view.*

class ConversationAdapter(private val conversationList: ArrayList<Conversation>) :
    RecyclerView.Adapter<ConversationAdapter.ViewHolder>(),ConversationClickListener {

    fun updateConversationList(newList: ArrayList<Conversation>) {
        conversationList.clear()
        conversationList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = conversationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.title.text = conversationList[position].title
        holder.view.discription.text = conversationList[position].description
        conversationList[position].adress?.let { holder.view.conversationIV.setImageResource(it) }
        holder.view.cardView.setOnClickListener {
            val action=ListFragmantDirections.actionListFragmantToSingleTalking(conversationList[position])
         Navigation.findNavController(holder.view).navigate(action)
            /* val intent=Intent(context, OneTalking::class.java)
             intent.putExtra("TalkNum",position)
             Log.d("clima","Conversationadapter -> onBind -> position -> $position")
             context.startActivity(intent)*/

        }
    }

    override fun onClick(v: View) {
        for (coversation in conversationList){
            if (v.tag==coversation.title){

            }
        }

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}





