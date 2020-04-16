package com.example.yhaa41.action

import android.app.Activity
import android.app.SearchManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.ThemedSpinnerAdapter
import com.example.yhaa41.R
import com.example.yhaa41.R.id.fab
import com.example.yhaa41.room.Para
import com.example.yhaa41.util.*
import com.github.florent37.viewanimator.ViewAnimator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.god_layout.*
import kotlinx.android.synthetic.main.helper_view_layout.*
import kotlinx.android.synthetic.main.man_layout.*
import java.util.*
import kotlin.collections.ArrayList

class AnimationInActionFragment : BaseFragment(),View.OnClickListener {
    lateinit var helper:Helper
    lateinit var pref:GetAndStoreData
    var para: Para? = null
    lateinit var activity:Activity


    private var tv0: TextView? = null
    private var tv0A: TextView? = null
    private var tv2A: TextView? = null
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null
    private var tv5: TextView? = null

    var listOfTextview = arrayListOf(tv0, tv1, tv2, tv3, tv4, tv5)
    var talkList=ArrayList<Talker>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity = context as Activity
        return inflater.inflate(R.layout.fragment_single_talking, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            helper= Helper(it)
            pref=GetAndStoreData(it)
        }
        arguments?.let {
            para=AnimationInActionFragmentArgs.fromBundle(it).para
            val gson = Gson()
            val jsonString= para!!.talkersString
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList = gson.fromJson(jsonString, type)
        }
        executeTalker()
    }
    fun executeTalker() {
        //updateTitleTalkerSituation()
        //  redGreenTvPage()
        val talker = talkList[para!!.currentPage]

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

        tv.setPadding(talker.padding[0], talker.padding[1], talker.padding[2], talker.padding[3])

        //   tv.setPadding(40, 40, 40, 40)
        tv.text = st.trim()

        return tv
    }
  /*  private fun updateTitleTalkerSituation() {

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
    }*/

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

    private fun initGodTextview(dur: Long) {
        ViewAnimator
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
        ViewAnimator
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
    private fun letsMove(talker: Talker, listOfTextview: ArrayList<TextView?>) {

        when (talker.animNum) {

            110 -> Utile.moveScale110(talker, listOfTextview)  //all together from the oposite corner
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

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.fab->nextIt()
            R.id.fab1->previousIt()
        }
        executeTalker()
    }

    private fun previousIt() {
        var currentPage= para?.currentPage!!
        currentPage--
        if (currentPage<1) currentPage=1
        para!!.currentPage=currentPage
    }

    private fun nextIt() {
      var max=talkList.size-1
        var currentPage= para?.currentPage!!
        currentPage++
        if (currentPage>max) currentPage=1
        para!!.currentPage=currentPage
    }
}

/*


class ButtonSpace(val context: Context) : View.OnClickListener {

    private val activity = context as Activity
    private val pref = GetAndStoreData(context)
//    private val animationInAction = AnimationInAction(context)
   // private val arrangeScreen = ArrangeScreen(context)
    val helper = Helper(context)

    private var statrTime: Long = 0
    private var endTime = System.nanoTime()

    fun drawAnim() {
        var showPosition = pref.getShowPosition()
        if (!showPosition) {
            updateTitleTalkerSituation()
        }
        // view.tvAnimatinKind.text = text
        val cu = getCurrentPage()
        activity.tvPage.text = cu.toString()
        pref.currentTalk().numTalker = cu
        //   updateTitleTalkerSituation()
        animationInAction.executeTalker()
    }

    fun letsPlay(v: View) {

        when (v.id) {
            R.id.textRevBtn -> readAgainTextFile()
            R.id.newPageBtn -> enterNewPage()
            R.id.showPositionBtn -> changeShowPosition()
            R.id.reActivateAnimation -> animationInAction.executeTalker()
            R.id.plusAndMinusBtn -> changePlusMinusMode()
            R.id.saveButton -> saveIt()
            R.id.nextButton -> nextIt()
            R.id.previousButton -> previousIt()
            R.id.lastTalker_button -> retriveLastTalker()
            R.id.reSizeTextBtn -> minTextSize()
            R.id.fab -> nextIt()
            R.id.fab1 -> previousIt()
            R.id.tvPage -> startPage()
            else -> drawAnim()

        }

    }


    private fun changeShowPosition() {
        var showPosition = pref.getShowPosition()
        showPosition = !showPosition
        pref.saveShowPosition(showPosition)
        setShowPositionMode()
        if (showPosition) activity.showPositionBtn.text = "toTest"
        else activity.showPositionBtn.text = "toShow"
    }

    fun setShowPositionMode() {
        var showPosition = pref.getShowPosition()
        var testMode=pref.getTestMode()

        with(activity) {
            if (!showPosition) {
                arrangeScreen.setLayoutShowMode()
                arrangeScreen.operateListView()


                plusAndMinusBtn.text = "+"
                lastTalker_button.text = "Last"
                saveButton.text = "Save"
                upper_layout.visibility = VISIBLE
                down_layout.visibility = VISIBLE

                textRevBtn.visibility = VISIBLE
                reSizeTextBtn.visibility = VISIBLE
                newPageBtn.visibility = VISIBLE
                reActivateAnimation.visibility = VISIBLE

                if (testMode) {
                    showPositionBtn.visibility = VISIBLE
                    showPositionBtn.text = "toShow"
                }else{
                    showPositionBtn.visibility = INVISIBLE

                }

                style_ListView.visibility = VISIBLE
                para_ListView.visibility = VISIBLE
                ttPara_listView.visibility = VISIBLE
                action_ListView.visibility = VISIBLE
                tvAnimatinKind.visibility = VISIBLE
                tvPage.visibility = VISIBLE
                fab.hide()
                fab1.hide()
            }

            if (showPosition) {
                helper.activateHowSpeaking()
                down_layout.visibility = INVISIBLE
                upper_layout.visibility = VISIBLE
                textRevBtn.visibility = INVISIBLE
                reSizeTextBtn.visibility = INVISIBLE
                newPageBtn.visibility = INVISIBLE
                reActivateAnimation.visibility = INVISIBLE
                if (testMode) {
                    showPositionBtn.visibility = VISIBLE
                    showPositionBtn.text = "toTest"
                }else{
                    showPositionBtn.visibility = INVISIBLE

                }
                style_ListView.visibility = INVISIBLE
                para_ListView.visibility = INVISIBLE
                ttPara_listView.visibility = INVISIBLE
                action_ListView.visibility = INVISIBLE
                tvAnimatinKind.visibility = INVISIBLE
                tvPage.visibility = VISIBLE
                fab.show()
                fab1.show()
            }
        }
    }

    private fun readAgainTextFile() {
        var list = ArrayList<Talker>()

        CoroutineScope(Dispatchers.IO).launch {
            val talkList = async { pref.getTalkingList(1) }
            val textTalkList = async { pref.createTalkListFromTheStart() }
            val talkList1 = async { textReRead(talkList.await(), textTalkList.await()) }
            pref.saveTalkingList(talkList1.await())
            list = talkList1.await()
            withContext(Dispatchers.Main) {
                delay(1000)
                drawAnim()
            }
        }
    }


    fun textReRead(
        talkList: ArrayList<Talker>,
        textTalkList: ArrayList<Talker>
    ): ArrayList<Talker> {
        for (index in 0..talkList.size - 1) {
            val st1 = textTalkList[index].taking
            var arr = st1.split("\n")
            val ar = arrayListOf<String>()
            for (item in arr) {
                if (item != "") {
                    ar.add(item)
                }
            }

            if (index > talkList.size) {
                var talk1 = textTalkList[index].copy()
                talkList.add(talk1)

            } else {

                talkList[index].takingArray = ar
                talkList[index].taking = textTalkList[index].taking
            }

            if (index == textTalkList.size - 1) {
                talkList.dropLast(talkList.size - textTalkList.size)
                return talkList
            }
        }
        return talkList
    }

    private fun minTextSize() {
        updateLastTalker(0)
        val list = pref.getTalkingList(1)
        val index = pref.getCurrentPage()
        list[index].textSize = 12f  // for accsident of bigest text
        pref.saveTalkingList(list)
        drawAnim()
    }

    private fun enterNewPage() {
        var myDialog = AlertDialog.Builder(context)
        val input = EditText(context)
        myDialog.setView(input)
        myDialog.setTitle("Enter new page")
        myDialog.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val num = input.text.toString().toInt()
                pref.saveCurrentPage(num)
                drawAnim()
                return
            }

        })
        myDialog.setNegativeButton("CANCEL", null)
        myDialog.show()
    }

    private fun retriveLastTalker() {
        updateLastTalker(1)
        drawAnim()
    }

    private fun updateLastTalker(ind: Int) {
        val talkList = pref.getTalkingList(1)
        with(pref) {
            if (ind == 0) {
                // val sa = pref.currentTalk()
                saveLastTalker(currentTalk())
            } else {
                talkList[this@ButtonSpace.getCurrentPage()] = getLastTalker().copy()
            }
        }
    }

    fun saveIt() {
        val talkList = pref.getTalkingList(1)

        pref.saveTalkingList(talkList)
        Toast.makeText(context, "It's save Mr", Toast.LENGTH_SHORT).show()
    }

    fun updateTitleTalkerSituation() {
        with(pref.currentTalk()) {
            val text =
                "l=${takingArray.size}sty=$styleNum anim=$animNum size=${textSize.toInt()}" +
                        " bord=$borderWidth dur=$dur sw=$swingRepeat"
            activity.tvAnimatinKind.text = text
        }
        activity.tvPage.text = pref.getCurrentPage().toString()
    }

    fun previousIt() {
        pref.saveLastTalker(pref.currentTalk())
        var cu = getCurrentPage()
        cu--
        pref.saveCurrentPage(cu)
        redGreenTvPage()
        animationInAction.executeTalker()
    }

    private fun startPage() {
        pref.saveLastTalker(pref.currentTalk())
        pref.saveCurrentPage(1)
        redGreenTvPage()
        animationInAction.executeTalker()
    }

    fun nextIt() {
        pref.saveLastTalker(pref.currentTalk())
        var cu = getCurrentPage()
        cu++
        val list=pref.getTalkingList(1)
        if (list.size-1<cu) cu=1
        pref.saveCurrentPage(cu)
        redGreenTvPage()
        animationInAction.executeTalker()
    }

    private fun changePlusMinusMode() {
        with(activity.plusAndMinusBtn) {
            if (text == "+") {
                text = "-"
            } else {
                text = "+"
            }
        }
    }

    override fun onClick(view: View) {
        letsPlay(view)
    }

    private fun time(st: String) {
        endTime = System.nanoTime()
        val interval = TimeUnit.MILLISECONDS.convert(endTime - statrTime, TimeUnit.NANOSECONDS)
        Log.d("clima", st + " --> $interval ms")

    }

      private fun redGreenTvPage() {
       val talker = pref.currentTalk()
       animationInActionSign(1, 500)
       activity.fab.isClickable = false
       activity.fab1.isClickable = false
       drawAnim()
       CoroutineScope(Main).launch {
           //delay(talker.dur + 200)
           var dela=talker.dur
           if (talker.animNum==111){
               dela=dela*talker.textSize.toLong()
           }
           delay(dela)
           activity.fab.isClickable = true
           activity.fab1.isClickable = true
           animationInActionSign(0, 500)
       }
   }

    private fun nextAndPriviousAction() {

        animationInActionSign(1, 500)
        activity.fab.isClickable = false
        activity.fab1.isClickable = false
        drawAnim()
        val talker = pref.currentTalk()
        CoroutineScope(Main).launch {
            //delay(talker.dur + 200)
            delay(talker.dur)
            activity.fab.isClickable = true
            activity.fab1.isClickable = true
            animationInActionSign(0, 500)
        }

    }


    fun animationInActionSign(ind: Int, dur: Long) {
        if (ind == 0) {
            ViewAnimator
                .animate(activity.tvPage)
                .backgroundColor(Color.RED, Color.GREEN)
                .duration(dur)
                .start()

        } else {
            ViewAnimator
                .animate(activity.tvPage)
                .backgroundColor(Color.GREEN, Color.RED)
                .duration(dur)
                .start()
        }
    }

/* private fun onClickOther(view: View) {
     val talkList = pref.getTalkingList(1)

     var def = 0
     if (view == activity.fab) {
         def++
     }
     if (view == activity.fab1) {
         def--
     }

     var counterStep = getCurrentPage() + def

     if (counterStep < 1) counterStep = 1
     if (counterStep == talkList.size) counterStep = 1
     pref.saveCurrentPage(counterStep)

     if (showPosition) {
         time("onClickA113")
         buttonActivation(0)

     }

     chageBackgroundColor(1, 1000)

     letsPlay(view)

     val size = pref.getLastTalker().takingArray.size
      Utile.listener1 = { it1, _ ->
         // Log.d("clima", "Hii num->$it1 and time->$it2 and size=$size")
         if (size == 1 || it1 == size) {
             time("onClickB114")
             buttonActivation(1)
             chageBackgroundColor(0, 1000)
         }
     }
 }*/

    fun getCurrentPage(): Int {
        val talkList = pref.getTalkingList(1)

        var cu = pref.getCurrentPage()
        if (cu < 1 || cu >= talkList.size) {
            cu = 1
            pref.saveCurrentPage(cu)
        }
        return cu
    }

    fun chageBackgroundColor(ind: Int, dur: Long) {
        if (ind == 0) {
            ViewAnimator
                .animate(activity.tvPage)
                .backgroundColor(Color.RED, Color.GREEN)
                .duration(dur)
                .start()

        } else {
            ViewAnimator
                .animate(activity.tvPage)
                .backgroundColor(Color.GREEN, Color.RED)
                .duration(dur)
                .start()
        }
    }

    fun fabAnimation(ind: Int) {
        if (ind == 0) {
            ViewAnimator
                .animate(activity.fab)
                .alpha(0f)
                .andAnimate(activity.fab1)
                .alpha(0f)
                .duration(2000)
                .start()
        } else {
            ViewAnimator
                .animate(activity.fab)
                .alpha(0f, 1f)
                .andAnimate(activity.fab1)
                .alpha(0f, 1f)
                .duration(2000)
                .start()
        }
    }


    fun initButton() {
        with(activity) {
            showPositionBtn.setOnClickListener { onClick(showPositionBtn) }
            textRevBtn.setOnClickListener { onClick(textRevBtn) }
            newPageBtn.setOnClickListener { onClick(newPageBtn) }
            plusAndMinusBtn.setOnClickListener { onClick(plusAndMinusBtn) }
            saveButton.setOnClickListener { onClick(saveButton) }
            nextButton.setOnClickListener { onClick(nextButton) }
            previousButton.setOnClickListener { onClick(previousButton) }
            lastTalker_button.setOnClickListener { onClick(lastTalker_button) }
            reSizeTextBtn.setOnClickListener { onClick(reSizeTextBtn) }
            reActivateAnimation.setOnClickListener { onClick(reActivateAnimation) }
            fab.setOnClickListener { onClick(fab) }
            fab1.setOnClickListener { onClick(fab1) }
            tvPage.setOnClickListener { onClick(tvPage) }
        }
        setShowPositionMode()
    }
}

/*fun buttonActivation(ind: Int) {
    time("buttonActivation 1 ind=$ind")
    var showPosition = pref.getShowPosition()


    with(activity) {
        if (ind == 0) {
            if (showPosition) {
                fab.isClickable = false
                fab1.isClickable = false
                fabAnimation(0)
            } else {
                textRevBtn.visibility = INVISIBLE
                newPageBtn.visibility = INVISIBLE
                toShowModeBtn.visibility = INVISIBLE
                plusAndMinusBtn.visibility = INVISIBLE
                showPositionBtn.visibility = INVISIBLE
                saveButton.visibility = INVISIBLE
                nextButton.visibility = INVISIBLE
                previousButton.visibility = INVISIBLE
                lastTalker_button.visibility = INVISIBLE
                reSizeTextBtn.visibility = INVISIBLE
            }
        }
        if (ind == 1) {
            if (showPosition) {
                fab.isClickable = true
                fab1.isClickable = true
                fabAnimation(1)
            } else {
                textRevBtn.visibility = VISIBLE
                newPageBtn.visibility = VISIBLE
                toShowModeBtn.visibility = INVISIBLE
                plusAndMinusBtn.visibility = VISIBLE
                showPositionBtn.visibility = VISIBLE
                saveButton.visibility = VISIBLE
                nextButton.visibility = VISIBLE
                previousButton.visibility = VISIBLE
                lastTalker_button.visibility = VISIBLE
                reSizeTextBtn.visibility = VISIBLE
            }
        }
    }
    time("buttonActivation 2 ind=$ind")
}*/
*/



