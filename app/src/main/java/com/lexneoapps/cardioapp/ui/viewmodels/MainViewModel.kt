package com.lexneoapps.cardioapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexneoapps.cardioapp.db.Cardio
import com.lexneoapps.cardioapp.db.CardioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardioDao: CardioDao
) : ViewModel() {


    fun insertCardio(cardio : Cardio) = viewModelScope.launch {
        cardioDao.insertCardio(cardio)
    }
}