package com.example.yhaa41.sentence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yhaa41.R
import com.example.yhaa41.ui.ListFragmantDirections
import kotlinx.android.synthetic.main.fragment_video_ftagment.*

/**
 * A simple [Fragment] subclass.
 */
class VideoFtagment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_ftagment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webView.settings.javaScriptEnabled=true
        webView.loadUrl("https://www.youtube.com/watch?v=rAOqP68wVS8")
       bakeToListOfSentence()
    }
    private fun bakeToListOfSentence(){
        /*val action1 = ListFragmantDirections.actionListFragmantToSentenceListFragment()
        Navigation.findNavController(it).navigate(action1)*/
        val action=VideoFtagmentDirections.actionVideoFtagmentToSentenceListFragment2()
        Navigation.findNavController(webView).navigate(action)
    }

}
