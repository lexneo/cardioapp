package com.lexneoapps.cardioapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.lexneoapps.cardioapp.db.CardioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val cardioDao: CardioDao
) : ViewModel() {

    val totalTimeRun = cardioDao.getTotalTimeInMillis()
    val totalDistance = cardioDao.getTotalDistance()
    val totalCaloriesBurned = cardioDao.getTotalCaloriesBurned()
    val totalAvgSpeed = cardioDao.getTotalAvgSpeed()

    val runsSortedByDate = cardioDao.getAllCardioSortedByDate()

}