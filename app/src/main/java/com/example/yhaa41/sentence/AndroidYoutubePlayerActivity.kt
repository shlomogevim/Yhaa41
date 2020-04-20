package com.example.yhaa41.sentence

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.yhaa41.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import kotlinx.android.synthetic.main.content_android_youtube_player.*
import kotlinx.android.synthetic.main.fragment_sentence_list.*
import java.util.regex.Pattern

class AndroidYoutubePlayerActivity() : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onBackPressed() {
         super.onBackPressed()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_youtube_player)



        var numV=intent.getIntExtra("video",1)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        third_party_player_view.getPlayerUiController().showFullscreenButton(true)
        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady( youTubePlayer: YouTubePlayer) {
                val videoId = setSourceUrl(numV)
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        third_party_player_view.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
            if (third_party_player_view.isFullScreen()) {
                third_party_player_view.exitFullScreen()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
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
    }

    private fun setSourceUrl(so:Int):String {
        val st = when (so){
            1-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
            2-> "https://www.youtube.com/watch?v=72-X0yPc3Hs"
            3-> "https://www.youtube.com/watch?v=pm3rDbXbZRI&list=RDpm3rDbXbZRI&start_radio=1"
            4->"https://www.youtube.com/watch?v=9UVjjcOUJLE&list=RD9UVjjcOUJLE&start_radio=1"
            else-> "https://www.youtube.com/watch?v=rAOqP68wVS8"
        }
        val url=getVideoId(st)
        return url
    }


    private val expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

    fun getVideoId(videoUrl:String): String {
        if (videoUrl == null || videoUrl.trim({ it <= ' ' }).length <= 0)
        {
            return null.toString()
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
        return null.toString()
    }
}
