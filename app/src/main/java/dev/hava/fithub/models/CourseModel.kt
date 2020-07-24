package dev.hava.fithub.models

import android.os.Parcel
import android.os.Parcelable

class CourseModel(
    val courseId: Int,
    val name: String,
    val price: Int,
    val courseStudentId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(courseStudentId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR :
        Parcelable.Creator<CourseModel> {
        override fun createFromParcel(parcel: Parcel): CourseModel {
            return CourseModel(parcel)
        }

        override fun newArray(size: Int): Array<CourseModel?> {
            return arrayOfNulls(size)
        }
    }
}