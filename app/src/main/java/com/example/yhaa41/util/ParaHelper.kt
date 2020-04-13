package com.example.yhaa41.util

import android.content.Context
import android.util.Log
import com.example.yhaa41.R
import com.example.yhaa41.Talker
import com.example.yhaa41.room.Para

class ParaHelper {


    fun creatTalkListFromTextFile(contex:Context, recognizer: Int): ArrayList<Talker> {

        var currenteFile = showFileName(recognizer)

        Log.d("clima","GetAndStoreData->FromTheStart1->recogniger1=$recognizer")

        var talkList1 = arrayListOf<Talker>()
        val ADAM = "-אדם-"
        val GOD = "-אלוהים-"
        var i = 0
        var countItem = 0
        var talker= Talker()
        talkList1.add(countItem, talker)

      //  var text = activity!!.assets.open(currenteFile).bufferedReader().use {
        var text = contex.assets.open(currenteFile).bufferedReader().use {
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





    fun setParaList():ArrayList<Para>{
        val paraList=ArrayList<Para>()
        var list = listOf<Para>(
            /*0*/
            Para(1,"תפזורת", "על מה שנכתב \n ועל מה שלא.", R.drawable.sea,"stam",1),
            /*1*/
            Para(2,"חנוך ילדים", "איך לא לחנך ילדים.", R.drawable.education,"stam",1),
            /*2*/
            Para(3,"משמעות החיים", "האם יש בכלל משמעות לחיים.", R.drawable.life,"stam",1),
            /*3*/
            Para(4,"פחד,ימי קורונה", "יצי קורונה,\nאיך להתמודד עם הפחד.", R.drawable.corona,"stam",1),
            /*4*/
            Para(5,"מי אני,מה אני", "על הגדרות וחוסר הגדרות.", R.drawable.man,"stam",1),
            /*5*/
            Para(6,"ריקנות", "בשיבחי הריקנות.", R.drawable.empty,"stam",1),

            Para(7,"בעבודה", "מה לעשות, עדיין בעבודה", R.mipmap.ic_launcher_round,"stam",1),
            Para(8,"בעבודה", "מה לעשות, עדיין בעבודה", R.mipmap.ic_launcher_round,"stam",1)
             )
        paraList.addAll((list))
        return paraList
    }
}