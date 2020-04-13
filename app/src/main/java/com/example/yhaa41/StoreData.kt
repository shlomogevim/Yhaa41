package com.example.yhaa41

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yhaa41.util.BaseFragment
import com.example.yhaa41.util.Talker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A simple [Fragment] subclass.
 */
class StoreData : BaseFragment() {

    lateinit var pref:SharedPreferences
   // val activity = context as Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
     //   pref= (this.activity?.getSharedPreferences(Const.PREFS_NAME, 0) ?:null) as SharedPreferences

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref= (this.activity?.getSharedPreferences(Const.PREFS_NAME, 0) ?:null) as SharedPreferences

    }

    fun saveCurrentPage(index: Int) {
        val recogniger = getRecognizer()
        pref.edit().putInt(Const.CURRENT_PAGE + recogniger.toString(), index).apply()
    }

    fun saveLastPage(index: Int) {
        pref.edit().putInt(Const.LAST_PAGE, index).apply()
    }

    fun saveCurrentePost(index:Int){
        pref.edit().putInt(Const.CURRENTSENTENCE,index).apply()
    }


    fun saveInterval(index: Int) {
        pref.edit().putInt(Const.INTERVAL, index).apply()
    }

    fun saveShowPosition(bo: Boolean) {
        pref.edit().putBoolean(Const.SHOWPOSITION, bo).apply()
    }

    fun saveTestMode(bo: Boolean) {
        pref.edit().putBoolean(Const.TESTMODE, bo).apply()
    }
    fun saveFirstTalk(bo: Boolean) {
        pref.edit().putBoolean(Const.FIRSTTALK, bo).apply()
    }

    fun saveFonts(index: Int) {
        val recogniger = getRecognizer()
        pref.edit().putInt(Const.FONTS + recogniger.toString(), index).apply()
    }

    fun saveRecognizer(index: Int) {
        pref.edit().putInt(Const.RECOGNIZER, index).apply()
    }

    fun getCurrentPage(): Int{
        val recogniger = getRecognizer()
        return pref.getInt(Const.CURRENT_PAGE + recogniger.toString(), 1)
    }
    fun getLastPage(): Int = pref.getInt(Const.LAST_PAGE, 1)
    fun getCurentPost():Int=pref.getInt(Const.CURRENTSENTENCE,100)
    fun getInterval(): Int = pref.getInt(Const.INTERVAL, 0)

    // fun getCurrentFile(): Int = pref.getInt(FILE_NUM, 1)
    fun getShowPosition(): Boolean = pref.getBoolean(Const.SHOWPOSITION, true)
    fun getTestMode(): Boolean = pref.getBoolean(Const.TESTMODE, false)
    fun getFirstTalk(): Boolean = pref.getBoolean(Const.FIRSTTALK, true)

    fun getFonts(): Int {
        val recogniger = getRecognizer()
        return pref.getInt(Const.FONTS + recogniger.toString(), 1)
    }
    fun getRecognizer(): Int = pref.getInt(Const.RECOGNIZER, 1)

    fun saveTalkingList(talkingList: ArrayList<Talker>) {
        val recogniger = getRecognizer()
        val gson = Gson()
        // val tagNum = getCurrentFile()
        val jsonString = gson.toJson(talkingList)
        // pref.edit().putString(TALKLIST+tagNum.toString(), jsonString).apply()
        pref.edit().putString(Const.TALKLIST + recogniger.toString(), jsonString).apply()
    }

    fun createList(reco:Int)=createTalkListFromTheStart1(reco)

    fun createListZero(recognizer1:Int){
        val talkList=createTalkListFromTheStart1(recognizer1)
        saveRecognizer(recognizer1)
        saveTalkingList(talkList)
        saveCurrentPage(1)
        saveLastTalker(talkList[1])
    }
    fun saveLastTalker(lastTalker: Talker) {
        val recogniger = getRecognizer()
        val gson = Gson()
        val jsonString = gson.toJson(lastTalker)
        pref.edit().putString(Const.LASTTALKER + recogniger.toString(), jsonString).apply()
    }
    fun getTalkingList(ind: Int): ArrayList<Talker> {
        val recogniger = getRecognizer()
        val talkList: ArrayList<Talker>
        val gson = Gson()
        val jsonString = pref.getString(Const.TALKLIST + recogniger.toString(), null)

        if (ind == 0 || jsonString == null) {
            talkList = createTalkListFromTheStart1(getRecognizer())
            saveTalkingList(talkList)
            saveCurrentPage(1)
            saveLastTalker(talkList[1])

        } else {
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList = gson.fromJson(jsonString, type)
        }
        return talkList
    }
    fun createTalkListFromTheStart1(recognizer1:Int): ArrayList<Talker> {

        var currenteFile = showFileName(recognizer1)

        Log.d("clima","GetAndStoreData->FromTheStart1->recogniger1=$recognizer1")

        var talkList1 = arrayListOf<Talker>()
        val ADAM = "-אדם-"
        val GOD = "-אלוהים-"
        var i = 0
        var countItem = 0
        var talker= Talker()
        talkList1.add(countItem, talker)

       var text = activity!!.assets.open(currenteFile).bufferedReader().use {
            it.readText()
        }
        text = text.replace("\r", "")
        var list1 = text.split(ADAM)

        for (element in list1) {
            //  if (element != "" && element.length > 25) {
            if (element != "") {
                i++
                var list2 = element.split(GOD)
                if (list2.size < 2) {
                    Log.i("clima", " $element   withoutgod")
                    return talkList1
                }
                var st1 = improveString(list2[0])
                var st2 = improveString(list2[1])
                if (st1.isNullOrEmpty() || st2.isNullOrEmpty()) {
                    return talkList1
                }
                countItem++
                talker = Talker()
                with(talker) {
                    whoSpeake = "man"
                    taking = st1.trim()
                    numTalker = countItem
                    var arr = st1.split("\n")
                    var ar = arr.toList()
                    arr = improveAr(ar)

                    if (arr.size > 6) {
                        //  setToastMessage(st1)
                        Log.i("clima", "st1->$st1")
                    }
                    for (item in arr) {
                        if (item.isNotBlank()) {
                            takingArray.add(item)
                        }
                    }
                    talker.numTalker = countItem
                    styleNum = 51
                    colorText = "#574339"
                    colorBack = "#fdd835"
                    borderColor = "#000000"
                    borderWidth = 0
                }
                talkList1.add(talker)
                countItem++
                talker = Talker()
                with(talker) {
                    whoSpeake = "god"
                    talker.taking = st2.trim()
                    var arr = st2.split("\n")
                    arr = improveAr(arr)
                    if (arr.size > 6) {
                        //  setToastMessage(st1)
                        Log.i("clima", "st1->$st1")
                    }
                    for (item in arr) {
                        if (item.isNotBlank()) {
                            takingArray.add(item)
                        }
                    }
                    talker.numTalker = countItem
                    styleNum = 51
                    colorText = "#574339"
                    colorBack = "#fdd835"
                    borderColor = "#000000"
                    borderWidth = 0

                }
                talkList1.add(talker)
            }
        }
        return talkList1
    }
    private fun showFileName(recognizer: Int): String {
        var currenteFile = ""
        when (recognizer) {
            1 -> currenteFile = "show/" + "text16" + ".txt"
            2 -> currenteFile = "show/" + "text15" + ".txt"
            3 -> currenteFile = "show/" + "text9" + ".txt"
            4 -> currenteFile = "show/" + "text7" + ".txt"
            5 -> currenteFile = "show/" + "text8" + ".txt"
            else-> currenteFile = "show/" + "text15" + ".txt"

        }
        return currenteFile
    }
    private fun improveString(st: String) = st.substring(1, st.length - 1)

    private fun improveAr(ar: List<String>): List<String> {
        var arr = ArrayList<String>()
        for (item in ar) {
            if (item.length > 1) {
                arr.add(item)
            }
        }



        return arr
    }
}
