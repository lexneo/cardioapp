package com.lexneoapps.cardioapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.lexneoapps.cardioapp.db.CardioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cardioDao: CardioDao
) : ViewModel() {

}