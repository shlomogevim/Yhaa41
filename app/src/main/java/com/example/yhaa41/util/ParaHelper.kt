package com.example.yhaa41.util

import com.example.yhaa41.R
import com.example.yhaa41.room.Para

class ParaHelper {
    fun setParaList():ArrayList<Para>{
        val paraList=ArrayList<Para>()
        var list = listOf<Para>(
            /*0*/
            Para("תפזורת", "על מה שנכתב \n ועל מה שלא.", R.drawable.sea),
            /*1*/
            Para("חנוך ילדים", "איך לא לחנך ילדים.", R.drawable.education),
            /*2*/
            Para("משמעות החיים", "האם יש בכלל משמעות לחיים.", R.drawable.life),
            /*3*/
            Para("פחד,ימי קורונה", "יצי קורונה,\nאיך להתמודד עם הפחד.", R.drawable.corona),
            /*4*/
            Para("מי אני,מה אני", "על הגדרות וחוסר הגדרות.", R.drawable.man),
            /*5*/
            Para("ריקנות", "בשיבחי הריקנות.", R.drawable.empty),

            Para("בעבודה", "מה לעשות, עדיין בעבודה", R.mipmap.ic_launcher_round),
            Para("בעבודה", "מה לעשות, עדיין בעבודה", R.mipmap.ic_launcher_round)
             )
        paraList.addAll((list))
        return paraList
    }
}