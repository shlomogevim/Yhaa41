package com.example.yhaa41.sentence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yhaa41.R
import com.example.yhaa41.ui.ListFragmantDirections
import com.example.yhaa41.ui.SingleTalkingArgs
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
        var sourceNum=1

        arguments?.let {
           sourceNum=VideoFtagmentArgs.fromBundle(it).videoNum
        }
        setSourceUrl(sourceNum)
       bakeToListOfSentence()
    }

    private fun setSourceUrl(so:Int) {
        val st = when (so){
            1-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
            2-> "https://www.youtube.com/watch?v=72-X0yPc3Hs"
            3-> "https://www.youtube.com/watch?v=pm3rDbXbZRI&list=RDpm3rDbXbZRI&start_radio=1"
            else-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
        }
        webView.loadUrl(st)
    }

    private fun bakeToListOfSentence(){
        val action=VideoFtagmentDirections.actionVideoFtagmentToSentenceListFragment2()
        Navigation.findNavController(webView).navigate(action)
    }

    /*
    * let my love be heard
    * https://www.youtube.com/watch?v=72-X0yPc3Hs
    * */

}
