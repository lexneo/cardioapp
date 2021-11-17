package com.lexneoapps.cardioapp.di

import android.content.Context
import androidx.room.Room
import com.lexneoapps.cardioapp.db.CardioDatabase
import com.lexneoapps.cardioapp.other.Constants.CARDIO_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCardioDatabase(
        @ApplicationContext app : Context

    ) = Room.databaseBuilder(
        app,
        CardioDatabase::class.java,
       CARDIO_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideCardioDao(db : CardioDatabase) = db.getCardioDao()
}