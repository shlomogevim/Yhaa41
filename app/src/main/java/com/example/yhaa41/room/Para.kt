package com.example.yhaa41.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Para(
    val title: String?,
    val description: String?,
    val imageInt:Int

):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(imageInt)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Para> {
        override fun createFromParcel(parcel: Parcel): Para {
            return Para(parcel)
        }

        override fun newArray(size: Int): Array<Para?> {
            return arrayOfNulls(size)
        }
    }
}

