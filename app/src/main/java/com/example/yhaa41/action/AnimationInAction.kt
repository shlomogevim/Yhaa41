package com.example.yhaa41.action

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import com.example.yhaa41.room.Para
import com.example.yhaa41.util.GetAndStoreData
import com.example.yhaa41.util.Helper
import com.example.yhaa41.util.Utile
import com.example.yhaa41.util.Talker
import kotlinx.android.synthetic.main.fragment_single_talking.*
import kotlinx.android.synthetic.main.god_layout.*
import kotlinx.android.synthetic.main.helper_view_layout.*
import kotlinx.android.synthetic.main.man_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class AnimationInAction(val context: Context): View.OnClickListener  {

        val activity = context as Activity
        val pref = GetAndStoreData(context)
        private val helper = Helper(context)

        private var tv0: TextView? = null
        private var tv0A: TextView? = null
        private var tv2A: TextView? = null
        private var tv1: TextView? = null
        private var tv2: TextView? = null
        private var tv3: TextView? = null
        private var tv4: TextView? = null
        private var tv5: TextView? = null

        var listOfTextview = arrayListOf(tv0, tv1, tv2, tv3, tv4, tv5)

        private fun styleTextViewTalk(tv: TextView, st: String, talker: Talker): TextView {
            val shape = GradientDrawable()
            shape.setCornerRadius(talker.radius)
            shape.setStroke(20, Color.parseColor(talker.borderColor))
            if (talker.colorBack == "none" || !talker.backExist) {
                shape.setColor(Color.TRANSPARENT)
                shape.setStroke(20, Color.TRANSPARENT)
            } else {
                try {
                    shape.setColor(Color.parseColor(talker.colorBack))
                    shape.setStroke(talker.borderWidth, Color.parseColor(talker.borderColor))
                } catch (e: Exception) {
                    shape.setColor(Color.parseColor("#000000"))
                }
            }
            tv.background = shape

            try {
                tv.setTextColor(Color.parseColor(talker.colorText))
            } catch (e: Exception) {
                tv.setTextColor(Color.parseColor("#ffffff"))
            }

            tv.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, talker.textSize)
            val font = pref.getFonts()
            tv.typeface = helper.getTypeFace(font)

            tv.setPadding(
                talker.padding[0],
                talker.padding[1],
                talker.padding[2],
                talker.padding[3]
            )

            //   tv.setPadding(40, 40, 40, 40)
            tv.text = st.trim()

            return tv
        }

        private fun updateTitleTalkerSituation() {
            val talker = pref.currentTalk()
            val index = pref.getCurrentPage()
            with(talker) {
                val newTalkerDetails =
                    "l=${takingArray.size}*sty=$styleNum*anim=$animNum*size=${textSize.toInt()}" +
                            "*bord=$borderWidth*dur=$dur sw=$swingRepeat"
                activity.tvAnimatinKind.text = newTalkerDetails
                activity.tvAnimatinKind.textSize = 11f

                activity.tvPage.text = index.toString()
                //  numTalker = index
            }
        }

        fun executeTalker(talker: Talker) {
            updateTitleTalkerSituation()
            //  redGreenTvPage()
           // val talker = pref.currentTalk()
            helper.activateHowSpeaking()

            if (talker.whoSpeake == "man") {
                configManTextView(talker)
            } else {
                configGodTextView(talker)
            }
            listOfTextview = arrayListOf(tv0, tv1, tv2, tv3, tv4, tv5)
            listOfTextview.removeAll(Collections.singleton(null))
            letsMove(talker, listOfTextview)

        }

        private fun redGreenTvPage() {
            val talker = pref.currentTalk()
            animationInActionSign(1, 500)
            activity.fab.isClickable = false
            activity.fab1.isClickable = false
            CoroutineScope(Dispatchers.Main).launch {
                var dela = talker.dur
                if (talker.animNum == 111 || talker.animNum == 121 || talker.animNum == 131) {
                    dela = dela * talker.takingArray.size.toLong()
                }
                delay(dela)
                activity.fab.isClickable = true
                activity.fab1.isClickable = true
                animationInActionSign(0, 500)
            }
        }

        fun animationInActionSign(ind: Int, dur: Long) {
            if (ind == 0) {
                com.github.florent37.viewanimator.ViewAnimator
                    .animate(activity.tvPage)
                    .backgroundColor(Color.RED, Color.GREEN)
                    .duration(dur)
                    .start()

            } else {
                com.github.florent37.viewanimator.ViewAnimator
                    .animate(activity.tvPage)
                    .backgroundColor(Color.GREEN, Color.RED)
                    .duration(dur)
                    .start()
            }
        }

        private fun letsMove(talker: Talker, listOfTextview: ArrayList<TextView?>) {

            when (talker.animNum) {

                110 -> Utile.moveScale110(
                    talker,
                    listOfTextview
                )  //all together from the oposite corner
                111 -> Utile.moveScaleWithDelay111(talker, listOfTextview)  //with delay
                120 -> Utile.scaleOnly120(talker, listOfTextview)  //all together
                121 -> Utile.scaleOnly121(talker, listOfTextview)  //with delay
                130 -> Utile.moveScaleRotateAll130(talker, listOfTextview)//all together
                131 -> Utile.moveScaleRotate131(talker, listOfTextview)  //with delay

                10 -> Utile.move_swing(10, talker, listOfTextview)
                11 -> Utile.move_swing(11, talker, listOfTextview)
                12 -> Utile.move_swing(12, talker, listOfTextview)
                13 -> Utile.move_swing(13, talker, listOfTextview)
                14 -> Utile.move_swing(14, talker, listOfTextview)
                15 -> Utile.move_swing(15, talker, listOfTextview)

                20 -> Utile.scale_swing(20, talker, listOfTextview)
                21 -> Utile.scale_swing(21, talker, listOfTextview)
                22 -> Utile.scale_swing(22, talker, listOfTextview)
                23 -> Utile.scale_swing(23, talker, listOfTextview)
                24 -> Utile.scale_swing(24, talker, listOfTextview)
                25 -> Utile.scale_swing(25, talker, listOfTextview)

                30 -> Utile.move_scale(30, listOfTextview, talker.dur)
                31 -> Utile.move_scale(31, listOfTextview, talker.dur)
                32 -> Utile.move_scale(32, listOfTextview, talker.dur)
                33 -> Utile.move_scale(33, listOfTextview, talker.dur)
                34 -> Utile.move_scale(34, listOfTextview, talker.dur)
                35 -> Utile.move_scale(35, listOfTextview, talker.dur)


                40 -> Utile.move_scale_rotate(40, talker, listOfTextview)
                41 -> Utile.move_scale_rotate(41, talker, listOfTextview)
                42 -> Utile.move_scale_rotate(42, talker, listOfTextview)
                43 -> Utile.move_scale_rotate(43, talker, listOfTextview)
                44 -> Utile.move_scale_rotate(44, talker, listOfTextview)
                45 -> Utile.move_scale_rotate(45, talker, listOfTextview)
                46 -> Utile.move_scale_rotate(46, talker, listOfTextview)

                50 -> Utile.apeareOneAfterAnother(listOfTextview, talker)
                51 -> Utile.apeareOneAfterAnotherAndSwing(listOfTextview, talker)

                else -> Utile.move_swing(0, talker, listOfTextview)
            }
        }

        private fun configGodTextView(talker: Talker) {
            initTextview()
            initGodTextview(1)

            val arr = talker.takingArray
            val size = arr.size

            tv0 = styleTextViewTalk(activity.godSpeaking0, arr[0], talker)
            if (size > 1) tv1 = styleTextViewTalk(activity.godSpeaking1, arr[1], talker)
            if (size > 2) tv2 = styleTextViewTalk(activity.godSpeaking2, arr[2], talker)
            if (size > 3) tv3 = styleTextViewTalk(activity.godSpeaking3, arr[3], talker)
            if (size > 4) tv4 = styleTextViewTalk(activity.godSpeaking4, arr[4], talker)
            if (size > 5) tv5 = styleTextViewTalk(activity.godSpeaking5, arr[5], talker)
            initManTextview(500)
        }

        private fun configManTextView(talker: Talker) {
            initTextview()
            initManTextview(1)
            val arr = talker.takingArray
            val size = arr.size
            if (size == 6) {
                tv0 = styleTextViewTalk(activity.manSpeaking0, arr[0], talker)
                if (size > 1) tv1 = styleTextViewTalk(activity.manSpeaking1, arr[1], talker)
                if (size > 2) tv2 = styleTextViewTalk(activity.manSpeaking2, arr[2], talker)
                if (size > 3) tv3 = styleTextViewTalk(activity.manSpeaking3, arr[3], talker)
                if (size > 4) tv4 = styleTextViewTalk(activity.manSpeaking4, arr[4], talker)
                if (size > 5) tv5 = styleTextViewTalk(activity.manSpeaking5, arr[5], talker)
            }
            if (size == 5) {
                tv0 = styleTextViewTalk(activity.manSpeaking1, arr[0], talker)
                if (size > 1) tv1 = styleTextViewTalk(activity.manSpeaking2, arr[1], talker)
                if (size > 2) tv2 = styleTextViewTalk(activity.manSpeaking3, arr[2], talker)
                if (size > 3) tv3 = styleTextViewTalk(activity.manSpeaking4, arr[3], talker)
                if (size > 4) tv4 = styleTextViewTalk(activity.manSpeaking5, arr[4], talker)
            }
            if (size == 4) {
                tv0 = styleTextViewTalk(activity.manSpeaking2, arr[0], talker)
                if (size > 1) tv1 = styleTextViewTalk(activity.manSpeaking3, arr[1], talker)
                if (size > 2) tv2 = styleTextViewTalk(activity.manSpeaking4, arr[2], talker)
                if (size > 3) tv3 = styleTextViewTalk(activity.manSpeaking5, arr[3], talker)
            }
            if (size == 3) {
                tv0 = styleTextViewTalk(activity.manSpeaking3, arr[0], talker)
                if (size > 1) tv1 = styleTextViewTalk(activity.manSpeaking4, arr[1], talker)
                if (size > 2) tv2 = styleTextViewTalk(activity.manSpeaking5, arr[2], talker)
            }
            if (size == 2) {
                tv0 = styleTextViewTalk(activity.manSpeaking4, arr[0], talker)
                if (size > 1) tv1 = styleTextViewTalk(activity.manSpeaking5, arr[1], talker)
            }
            if (size == 1) {
                tv0 = styleTextViewTalk(activity.manSpeaking5, arr[0], talker)
            }
            initGodTextview(500)
        }

        private fun initGodTextview(dur: Long) {
            com.github.florent37.viewanimator.ViewAnimator
                .animate(
                    activity.godSpeaking0,
                    activity.godSpeaking1,
                    activity.godSpeaking2,
                    activity.godSpeaking3,
                    activity.godSpeaking4,
                    activity.godSpeaking5
                )
                .scale(0f)
                .duration(dur)
                .start()
        }

        private fun initManTextview(dur: Long) {
            com.github.florent37.viewanimator.ViewAnimator
                .animate(
                    activity.manSpeaking0,
                    activity.manSpeaking1,
                    activity.manSpeaking2,
                    activity.manSpeaking3,
                    activity.manSpeaking4,
                    activity.manSpeaking5
                )
                .scale(0f)
                .duration(dur)
                .start()
        }


        private fun initTextview() {
            tv0 = null
            tv0A = null
            tv2A = null
            tv1 = null
            tv2 = null
            tv3 = null
            tv4 = null
            tv5 = null
        }

    override fun onClick(v: View?) {

    }



}








