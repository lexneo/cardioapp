package com.lexneoapps.cardioapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardio(cardio: Cardio)

    @Delete
    suspend fun deleteCardio(cardio: Cardio)


    /* //Similar but shorter
     @Query("""
         SELECT * FROM running_table
         ORDER BY
         CASE WHEN :column = 'timestamp'  THEN timestamp END DESC,
         CASE WHEN :column = 'timeInMillis' THEN timeInMillis END DESC,
         CASE WHEN :column = 'calories' THEN caloriesBurned END DESC,
         CASE WHEN :column = 'speed'  THEN avgSpeedInKMH END DESC,
         CASE WHEN :column = 'distance' THEN distanceInMeters END DESC,
     """)
     suspend fun filterBy(column : String) : LiveData<List<Run>>*/

    @Query("SELECT * FROM cardio_table ORDER BY timeStamp DESC")
    fun getAllCardioSortedByDate(): LiveData<List<Cardio>>

    @Query("SELECT * FROM cardio_table ORDER BY timeInMillis DESC")
    fun getAllCardioSortedByTimeInMillis(): LiveData<List<Cardio>>

    @Query("SELECT * FROM cardio_table ORDER BY caloriesBurned DESC")
    fun getAllCardioSortedByCaloriesBurned(): LiveData<List<Cardio>>

    @Query("SELECT * FROM cardio_table ORDER BY avgSpeedInKMH DESC")
    fun getAllCardioSortedByAvgSpeed(): LiveData<List<Cardio>>

    @Query("SELECT * FROM cardio_table ORDER BY distanceInMeters DESC")
    fun getAllCardioSortedByDistance(): LiveData<List<Cardio>>

    @Query("SELECT SUM(timeInMillis) FROM cardio_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM cardio_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM cardio_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM cardio_table")
    fun getTotalAvgSpeed(): LiveData<Float>

}