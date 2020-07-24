package dev.hava.fithub.models

import android.os.Parcel
import android.os.Parcelable

class HistoryModel(
    val historyId: Int,
    val historyType: Int,
    val details: String,
    val value: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(historyId)
        parcel.writeInt(historyType)
        parcel.writeString(details)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR :
        Parcelable.Creator<HistoryModel> {
        override fun createFromParcel(parcel: Parcel): HistoryModel {
            return HistoryModel(parcel)
        }

        override fun newArray(size: Int): Array<HistoryModel?> {
            return arrayOfNulls(size)
        }
    }
}