package com.lexneoapps.cardioapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lexneoapps.cardioapp.other.Constants


@Entity(tableName = "cardio_table")
data class Cardio(
    var img: Bitmap? = null,
    var type: String = Constants.CARDIO_RUN,
    var timeStamp: Long = 0L,
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var timeInMillis: Long = 0L,
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}