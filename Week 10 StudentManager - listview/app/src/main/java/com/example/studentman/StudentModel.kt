package com.example.studentman

import android.os.Parcel
import android.os.Parcelable

data class StudentModel(
    val studentName: String,
    val studentId: String
) : Parcelable {
    // Constructor for Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    // Must-have method for Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(studentName)
        parcel.writeString(studentId)
    }

    // Must-have method for Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Companion object for Parcelable
    companion object CREATOR : Parcelable.Creator<StudentModel> {
        override fun createFromParcel(parcel: Parcel): StudentModel {
            return StudentModel(parcel)
        }

        override fun newArray(size: Int): Array<StudentModel?> {
            return arrayOfNulls(size)
        }
    }

    // Override toString for ListView display
    override fun toString(): String {
        return "$studentName - $studentId"
    }
}