package com.example.sleepqualitytracker.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import java.util.Date

@VersionedParcelize
@Entity(tableName = "sleep_quality_table")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startTimeMillis: Long = Date().time,
    var endTimeMillis: Long = startTimeMillis,
    var quality: Int = -1 // not yet rated
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(startTimeMillis)
        parcel.writeLong(endTimeMillis)
        parcel.writeInt(quality)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SleepEntity> {
        override fun createFromParcel(parcel: Parcel): SleepEntity {
            return SleepEntity(parcel)
        }

        override fun newArray(size: Int): Array<SleepEntity?> {
            return arrayOfNulls(size)
        }
    }
}