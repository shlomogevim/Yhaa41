package com.example.yhaa41.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yhaa41.R
import com.example.yhaa41.room.Para
import kotlinx.android.synthetic.main.list_row.view.*

class ParaAdapter(val paras: List<Para>) :
    RecyclerView.Adapter<ParaAdapter.ParaViewHolder>() {

    class ParaViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParaViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.list_row, parent, false)
        return ParaViewHolder(view)
    }

    override fun getItemCount() = paras.size
    override fun onBindViewHolder(holder: ParaViewHolder, position: Int) {
        holder.view.title.text = paras[position].title
        holder.view.discription.text = paras[position].description
        paras[position].imageInt?.let { holder.view.paraIV.setImageResource(it) }

        holder.view.setOnClickListener {
            if (position > 0) {
                val action = ListFragmantDirections.actionListFragmantToSingleTalking()
                action.para = paras[position]
                Navigation.findNavController(it).navigate(action)
            } else {
                val action1 = ListFragmantDirections.actionListFragmantToSentenceListFragment()
                Navigation.findNavController(it).navigate(action1)
            }
        }
    }

    private fun operateMovment(it: View?, position: Int) {
        if (position > 1) {
            val action = ListFragmantDirections.actionListFragmantToSingleTalking()
            action.para = paras[position]
            it?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        } else {
            // val action=ListFragmantDirections.a
        }
    }
}



