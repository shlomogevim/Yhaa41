package com.example.yhaa41.util

import com.example.yhaa41.R
import com.example.yhaa41.room.Para

class ParaHelper {







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