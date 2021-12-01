package com.lexneoapps.cardioapp.ui.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexneoapps.cardioapp.db.Cardio
import com.lexneoapps.cardioapp.db.CardioDao
import com.lexneoapps.cardioapp.other.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardioDao: CardioDao
) : ViewModel() {

    private val cardioSortedByDate = cardioDao.getAllCardioSortedByDate()
    private val cardioSortedByDistance = cardioDao.getAllCardioSortedByDistance()
    private val cardioSortedByCaloriesBurned = cardioDao.getAllCardioSortedByCaloriesBurned()
    private val cardioSortedByTimeInMillis = cardioDao.getAllCardioSortedByTimeInMillis()
    private val cardioSortedByAvgSpeed = cardioDao.getAllCardioSortedByAvgSpeed()

    val cardios = MediatorLiveData<List<Cardio>>()

    var sortType = SortType.DATE

    init {
        cardios.addSource(cardioSortedByDate){result ->
            if (sortType == SortType.DATE){
                result?.let { cardios.value = it }
            }
        }
        cardios.addSource(cardioSortedByAvgSpeed){result ->
            if (sortType == SortType.AVG_SPEED){
                result?.let { cardios.value = it }
            }
        }
        cardios.addSource(cardioSortedByCaloriesBurned){result ->
            if (sortType == SortType.CALORIES_BURNED){
                result?.let { cardios.value = it }
            }
        }
        cardios.addSource(cardioSortedByDistance){result ->
            if (sortType == SortType.DISTANCE){
                result?.let { cardios.value = it }
            }
        }
        cardios.addSource(cardioSortedByTimeInMillis){result ->
            if (sortType == SortType.RUNNING_TIME){
                result?.let { cardios.value = it }
            }
        }
    }

    fun sortCardio(sortType: SortType) = when(sortType){
        SortType.DATE -> cardioSortedByDate.value?.let { cardios.value = it }
        SortType.RUNNING_TIME -> cardioSortedByTimeInMillis.value?.let { cardios.value = it }
        SortType.AVG_SPEED -> cardioSortedByAvgSpeed.value?.let { cardios.value = it }
        SortType.DISTANCE -> cardioSortedByDistance.value?.let { cardios.value = it }
        SortType.CALORIES_BURNED -> cardioSortedByCaloriesBurned.value?.let { cardios.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertCardio(cardio: Cardio) = viewModelScope.launch {
        cardioDao.insertCardio(cardio)
    }
}