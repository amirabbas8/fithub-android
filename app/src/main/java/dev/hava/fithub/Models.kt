package dev.hava.fithub

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

    companion object CREATOR : Parcelable.Creator<HistoryModel> {
        override fun createFromParcel(parcel: Parcel): HistoryModel {
            return HistoryModel(parcel)
        }

        override fun newArray(size: Int): Array<HistoryModel?> {
            return arrayOfNulls(size)
        }
    }
}

class CourseModel(
    val courseId: Int,
    val name: String,
    val price: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeString(name)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseModel> {
        override fun createFromParcel(parcel: Parcel): CourseModel {
            return CourseModel(parcel)
        }

        override fun newArray(size: Int): Array<CourseModel?> {
            return arrayOfNulls(size)
        }
    }
}

class PostModel(
    val postId: Int,
    val text: String,
    val order: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeString(text)
        parcel.writeInt(order)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostModel> {
        override fun createFromParcel(parcel: Parcel): PostModel {
            return PostModel(parcel)
        }

        override fun newArray(size: Int): Array<PostModel?> {
            return arrayOfNulls(size)
        }
    }
}
