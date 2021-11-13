package com.lexneoapps.cardioapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Cardio::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class CardioDatabase : RoomDatabase(){

    abstract fun getCardioDao() : CardioDao
}