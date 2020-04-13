package com.example.yhaa41.util

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import java.io.Serializable

data class Talker(
    var whoSpeake: String = "man",
    var taking: String = "tadam",
    var takingArray: ArrayList<String> = arrayListOf(),
    var styleNum: Int = 0,
    var colorBack: String = "#000000",
    var colorText: String = "#fdd835",

    var numTalker: Int = 0,
    var animNum: Int = 120,
    var dur: Long = 3000,
    var textSize: Float = 20f,

    var backExist: Boolean = true,


    var radius: Float = 24f,
    var padding: ArrayList<Int> = arrayListOf(10, 0, 10, 0),
    var borderColor: String = "#000000",
    var borderWidth: Int = 0,
    var swingRepeat: Int = 0
) : Serializable

class StyleObject(
    val numStyleObject: Int = 0,
    val colorBack: String = "none",
    val colorText: String = "#ffffff",
    val sizeText: Float = 20f,
    val fontText: Int = 10
) : Serializable



data class Conversation(
    val title:String?=null,
    val description:String?=null,
    val adress:Int?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(adress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Conversation> {
        override fun createFromParcel(parcel: Parcel): Conversation {
            return Conversation(parcel)
        }

        override fun newArray(size: Int): Array<Conversation?> {
            return arrayOfNulls(size)
        }
    }
}

data class Sentence(
    val firstPartSen:String,
    val secondPartSen:String
)




