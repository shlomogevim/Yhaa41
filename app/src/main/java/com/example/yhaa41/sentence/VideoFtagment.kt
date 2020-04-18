package com.example.yhaa41.sentence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.yhaa41.R
import com.example.yhaa41.ui.ListFragmantDirections
import com.example.yhaa41.ui.SingleTalkingArgs
import com.example.yhaa41.ui.SingleTalkingDirections
import com.example.yhaa41.util.BaseFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_video_ftagment.*
import kotlinx.android.synthetic.main.god_layout.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass.
 */
class VideoFtagment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback =
            requireActivity().onBackPressedDispatcher.addCallback(this) {
                val action = VideoFtagmentDirections.actionVideoFtagmentToSentenceListFragment()
                Navigation.findNavController(webView).navigate(action)
            }
        callback.isEnabled
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_video_ftagment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      /*  third_party_player_view.getPlayerUiController().showFullscreenButton(true)
        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "YE&Vzlltp-4"
                youTubePlayer.cueVideo(videoId, 0f)
                //super.onReady(youTubePlayer)
            }
        })

        third_party_player_view.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
            if (third_party_player_view.isFullScreen()){
                third_party_player_view.exitFullScreen()
               // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

            }
        })*/

        /*third_party_player_view.getPlayerUiController()
            .setFullScreenButtonClickListener(View.OnClickListener {
                if (third_party_player_view.isFullScreen()) {
                    third_party_player_view.exitFullScreen()
                   ## window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    // Show ActionBar
                    if (supportActionBar != null) {
                        supportActionBar!!.show()
                    }
                } else {
                    third_party_player_view.enterFullScreen()
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                    // Hide ActionBar
                    if (supportActionBar != null) {
                        supportActionBar!!.hide()
                    }
                }
            })
    }*/


            webView.settings.javaScriptEnabled = true
            var sourceNum = 1

            arguments?.let {
                sourceNum = VideoFtagmentArgs.fromBundle(it).videoNum
            }
            setSourceUrl(sourceNum)



         backeToListOfSentence()

    }



    private fun setSourceUrl(so:Int) {
        val st = when (so){
            1-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
            2-> "https://www.youtube.com/watch?v=72-X0yPc3Hs"
            3-> "https://www.youtube.com/watch?v=pm3rDbXbZRI&list=RDpm3rDbXbZRI&start_radio=1"
             4->"https://www.youtube.com/watch?v=9UVjjcOUJLE&list=RD9UVjjcOUJLE&start_radio=1"
            else-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
        }
        webView.loadUrl(st)
    }


   private val expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

    fun getVideoId(videoUrl:String): String? {
        if (videoUrl == null || videoUrl.trim({ it <= ' ' }).length <= 0)
        {
            return null
        }
        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(videoUrl)
        try
        {
            if (matcher.find())
                return matcher.group()
        }
        catch (ex:ArrayIndexOutOfBoundsException) {
            ex.printStackTrace()
        }
        return null
    }


    private fun backeToListOfSentence(){
        val action=VideoFtagmentDirections.actionVideoFtagmentToSentenceListFragment()
        Navigation.findNavController(webView).navigate(action)
    }



}
