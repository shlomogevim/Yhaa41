package com.example.yhaa41

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yhaa41.Const.Companion.CURRENTSENTENCE
import com.example.yhaa41.Const.Companion.CURRENT_PAGE
import com.example.yhaa41.Const.Companion.FIRSTTALK
import com.example.yhaa41.Const.Companion.FONTS
import com.example.yhaa41.Const.Companion.INTERVAL
import com.example.yhaa41.Const.Companion.LASTTALKER
import com.example.yhaa41.Const.Companion.LAST_PAGE
import com.example.yhaa41.Const.Companion.PREFS_NAME
import com.example.yhaa41.Const.Companion.RECOGNIZER
import com.example.yhaa41.Const.Companion.SHOWPOSITION
import com.example.yhaa41.Const.Companion.TALKLIST
import com.example.yhaa41.Const.Companion.TESTMODE

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class GetAndStoreData(val context: Context) : AppCompatActivity() {


    var myPref = context.getSharedPreferences(PREFS_NAME, 0)
   // val recogniger = getRecognizer()

    //  private var talkList = getTalkingList(1)


    fun saveCurrentPage(index: Int) {
        val recogniger = getRecognizer()
        myPref.edit().putInt(CURRENT_PAGE + recogniger.toString(), index).apply()
    }

    fun saveLastPage(index: Int) {
        myPref.edit().putInt(LAST_PAGE, index).apply()
    }

    fun saveCurrentePost(index:Int){
        myPref.edit().putInt(CURRENTSENTENCE,index).apply()
    }


    fun saveInterval(index: Int) {
        myPref.edit().putInt(INTERVAL, index).apply()
    }

    fun saveShowPosition(bo: Boolean) {
        myPref.edit().putBoolean(SHOWPOSITION, bo).apply()
    }

    fun saveTestMode(bo: Boolean) {
        myPref.edit().putBoolean(TESTMODE, bo).apply()
    }
    fun saveFirstTalk(bo: Boolean) {
        myPref.edit().putBoolean(FIRSTTALK, bo).apply()
    }

    fun saveFonts(index: Int) {
        val recogniger = getRecognizer()
        myPref.edit().putInt(FONTS + recogniger.toString(), index).apply()
    }

    fun saveRecognizer(index: Int) {
        myPref.edit().putInt(RECOGNIZER, index).apply()
    }

    fun getCurrentPage(): Int{
        val recogniger = getRecognizer()
        return myPref.getInt(CURRENT_PAGE + recogniger.toString(), 1)
    }
    fun getLastPage(): Int = myPref.getInt(LAST_PAGE, 1)
    fun getCurentPost():Int=myPref.getInt(CURRENTSENTENCE,100)
    fun getInterval(): Int = myPref.getInt(INTERVAL, 0)

    // fun getCurrentFile(): Int = myPref.getInt(FILE_NUM, 1)
    fun getShowPosition(): Boolean = myPref.getBoolean(SHOWPOSITION, true)
    fun getTestMode(): Boolean = myPref.getBoolean(TESTMODE, false)
    fun getFirstTalk(): Boolean = myPref.getBoolean(FIRSTTALK, true)

    fun getFonts(): Int {
        val recogniger = getRecognizer()
        return myPref.getInt(FONTS + recogniger.toString(), 1)
    }
    fun getRecognizer(): Int = myPref.getInt(RECOGNIZER, 1)

    fun currentTalk(): Talker {
        val list = getTalkingList(1)
        var index = getCurrentPage()
        if (index > list.size - 1) {
            index = index--
            if (index < 1) index = 1
            Toast.makeText(context, "sorry ther is more then 6 lines", Toast.LENGTH_LONG).show()
        }
        return list[index]
    }


    fun saveTalkingList(talkingList: ArrayList<Talker>) {
        val recogniger = getRecognizer()
        val gson = Gson()
        // val tagNum = getCurrentFile()
        val jsonString = gson.toJson(talkingList)
        // myPref.edit().putString(TALKLIST+tagNum.toString(), jsonString).apply()
        myPref.edit().putString(TALKLIST + recogniger.toString(), jsonString).apply()
    }

    fun saveLastTalker(lastTalker: Talker) {
        val recogniger = getRecognizer()
        val gson = Gson()
        val jsonString = gson.toJson(lastTalker)
        myPref.edit().putString(LASTTALKER + recogniger.toString(), jsonString).apply()
    }

    fun getLastTalker(): Talker {
        val recogniger = getRecognizer()
        var talker = Talker()
        var jsonS = myPref.getString(LASTTALKER + recogniger.toString(), null)
        if (jsonS != null) {
            val gson = Gson()
            val type = object : TypeToken<Talker>() {}.type
            talker = gson.fromJson(jsonS, type)
        }
        return talker
    }

    fun getTalkingList(ind: Int): ArrayList<Talker> {
        val recogniger = getRecognizer()
        val talkList: ArrayList<Talker>
        val gson = Gson()
        val jsonString = myPref.getString(TALKLIST + recogniger.toString(), null)

        if (ind == 0 || jsonString == null) {
            talkList = createTalkListFromTheStart()
            saveTalkingList(talkList)
            saveCurrentPage(1)
            saveLastTalker(talkList[1])

        } else {
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList = gson.fromJson(jsonString, type)
        }
        return talkList
    }

    fun createListZero(recognizer1:Int){
        val talkList=createTalkListFromTheStart1(recognizer1)
        saveRecognizer(recognizer1)
        saveTalkingList(talkList)
        saveCurrentPage(1)
        saveLastTalker(talkList[1])
    }

    private fun setToastMessage(st: String) {
        val st0 = "במשפט  :"
        val str1 = "$st there is more 6 lines"
        Toast.makeText(context, st0 + str1, Toast.LENGTH_LONG).show()


    }

    fun createTalkListFromTheStart1(recognizer1:Int): ArrayList<Talker> {

        var currenteFile = showFileName(recognizer1)

        Log.d("clima","GetAndStoreData->FromTheStart1->recogniger1=$recognizer1")

        var talkList1 = arrayListOf<Talker>()
        val ADAM = "-אדם-"
        val GOD = "-אלוהים-"
        var i = 0
        var countItem = 0
        var talker=Talker()
        talkList1.add(countItem, talker)
        var text = context.assets.open(currenteFile).bufferedReader().use {
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

    fun createTalkListFromTheStart(): ArrayList<Talker> {
        val recognizer = getRecognizer()
        var currenteFile = showFileName(recognizer)

        Log.d("clima","GetAndStoreData->FromTheStart->recogniger=$recognizer")

        var talkList1 = arrayListOf<Talker>()
        val ADAM = "-אדם-"
        val GOD = "-אלוהים-"
        var i = 0
        var countItem = 0
        var talker=Talker()
        talkList1.add(countItem, talker)
        var text = context.assets.open(currenteFile).bufferedReader().use {
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

    private fun improveAr(ar: List<String>): List<String> {
        var arr = ArrayList<String>()
        for (item in ar) {
            if (item.length > 1) {
                arr.add(item)
            }
        }



        return arr
    }

    /*  fun getTalkingList(ind: Int): ArrayList<Talker> {
          val talkList: ArrayList<Talker>
          val gson = Gson()
          val jsonString = myPref.getString(TALKLIST, null)

          if (ind == 0 || jsonString == null) {
              talkList = createTalkListFromTheStart()
              saveTalkingList(talkList)
              saveCurrentPage(1)
              saveLastTalker(talkList[1])

          } else {
              val type = object : TypeToken<ArrayList<Talker>>() {}.type
              talkList = gson.fromJson(jsonString, type)
          }
          return talkList
      }*/

    /* fun createTalkListFromTheStart(): ArrayList<Talker> {
         var talkList1 = arrayListOf<Talker>()
         val ADAM = "-אדם-"
         val GOD = "-אלוהים-"
         val currenteFile = "text/text" + ASSEETS_FILE + ".txt"

         var countItem = 0
         var text = context.assets.open(currenteFile).bufferedReader().use {
             it.readText()
         }
         text = text.replace("\r", "")
         var list1 = text.split(ADAM)

         var talker = Talker()

         talkList1.add(countItem, talker)
         var i = 0

         for (element in list1) {
             //  if (element != "" && element.length > 25) {
             if (element != "") {
                 i++
                 var list2 = element.split(GOD)
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
                     for (item in arr) {
                         if (item != "") {
                             takingArray.add(item)
                         }
                     }
                     colorText = "#000000"
                     colorBack = "#ffffff"
                     animNum = 10
                 }

                 talkList1.add(talker)

                 countItem++
                 talker = Talker()
                 with(talker) {
                     whoSpeake = "god"
                     talker.taking = st2.trim()
                     talker.numTalker = countItem
                     var arr = st2.split("\n")
                     for (item in arr) {
                         if (item != "") {
                             takingArray.add(item)
                         }
                     }
                     colorText = "#000000"
                     colorBack = "#ffffff"
                     animNum = 10
                 }
                 talkList1.add(talker)
             }
         }
         return talkList1
     }*/

    private fun improveString(st: String) = st.substring(1, st.length - 1)

    /*fun saveCurrentFile(index: Int) {
        myPref.edit().putInt(FILE_NUM, index).apply()
    }*/

    private fun createTalkArray(jsonString: String?) {
        var talkList: ArrayList<Talker>
        //  Log.d("clima",jsonString)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Talker>>() {}.type
        talkList = gson.fromJson(jsonString, type)
        Log.d("clima", "${talkList[19].taking}")
    }

    fun createNewList(): ArrayList<Talker> {
        var talkList1 = ArrayList<Talker>()
        //  val tagNum = getCurrentFile()

        // var jsonS =  myPref.getString(TALKLIST+tagNum.toString(), null)
        var jsonS = myPref.getString(TALKLIST, null)
        if (jsonS != null) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList1 = gson.fromJson(jsonS, type)
            //saveTalkingListInPref(talkList1)
        }
        return talkList1
    }

    fun saveJsonString(jsonS: String?) {
        myPref.edit().putString(TALKLIST, jsonS).apply()
    }

    fun getJsonArryFromPref(): ArrayList<Talker> {
        var list = ArrayList<Talker>()
        var jsonS: String?
        jsonS = myPref.getString(TALKLIST, null)
        if (!jsonS.isNullOrEmpty()) {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            list = gson.fromJson(jsonS, type)
        }
        return list
    }


    fun createTalkList(): ArrayList<Talker> {
        var talkList: ArrayList<Talker>
        val jsonString = myPref.getString(TALKLIST, null)

        // val jsonString = intent.getStringExtra(JSONSTRING)
        // val jsonString = intent.getStringExtra(JSONSTRING)
        if (jsonString == "none" || jsonString == "") {
            talkList = getTalkingList(0)
            saveTalkingList(talkList)

        } else {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Talker>>() {}.type
            talkList = gson.fromJson(jsonString, type)
            saveTalkingList(talkList)
        }
        return talkList
    }

    private fun decodebase64(input: String): Bitmap {
        val decodeByte = Base64.decode(input, 0)
        val bit = BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
        return bit
    }

    private fun encodeToBase64(image: Bitmap): String {
        val immage = image
        val baos = ByteArrayOutputStream()
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)
        Log.d("clima", "imageEncode->$imageEncoded")
        return imageEncoded
    }
}