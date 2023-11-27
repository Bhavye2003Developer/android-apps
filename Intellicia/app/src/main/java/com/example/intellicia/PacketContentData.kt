package com.example.intellicia


import android.os.Parcel
import android.os.Parcelable

data class PacketContentData(var content: String? = "") : Parcelable {

    // Constructor used for parceling
    constructor(parcel: Parcel) : this(parcel.readString())

    // Write object's data to the parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
    }

    // Return a bitmask indicating the set of special object types
    override fun describeContents(): Int {
        return 0
    }

    // This is used to regenerate the object. All Parcelables must have a CREATOR that implements these two methods
    companion object CREATOR : Parcelable.Creator<PacketContentData> {
        // Create a new instance of the Parcelable class, instantiating it from the given parcel
        override fun createFromParcel(parcel: Parcel): PacketContentData {
            return PacketContentData(parcel)
        }

        // Create a new array of the Parcelable class
        override fun newArray(size: Int): Array<PacketContentData?> {
            return arrayOfNulls(size)
        }
    }
}
