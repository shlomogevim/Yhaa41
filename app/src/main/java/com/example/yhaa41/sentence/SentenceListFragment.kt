package com.example.yhaa41.sentence

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.yhaa41.R
import com.example.yhaa41.util.Helper

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.fragment_sentence_list.*

/**
 * A simple [Fragment] subclass.
 */
class SentenceListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sentence_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            Helper.Sent.createSentList()
            var senteList = Helper.Sent.sentList
            val snapeHelper = GravitySnapHelper(Gravity.CENTER)

            recyclerViewPostId.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            snapeHelper.attachToRecyclerView(recyclerViewPostId)
            recyclerViewPostId.adapter = context?.let { SentenceListAdapter(it,senteList) }
            recyclerViewPostId.adapter!!.notifyDataSetChanged()
        }
    }


