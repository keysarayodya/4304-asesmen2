package org.d3if0044.bangundatar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0044.bangundatar.data.local.BangunDatarDao
import org.d3if0044.bangundatar.data.local.BangunDatarEntity

class MenuFragmentViewModel(private val db: BangunDatarDao) : ViewModel() {
    private val _savedCalculation = db.getNote()

    val savedCalculation: LiveData<List<BangunDatarEntity>>
        get() = _savedCalculation

    fun deleteSavedCalculation(savedCalculation: BangunDatarEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteNote(savedCalculation)
        }
    }

    fun updateSavedCalculation(savedCalculation: BangunDatarEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.updateNote(savedCalculation)
        }
    }

    fun addSavedCalculation(
        type: String,
        value1: String,
        value2: String,
        area: String,
        circumference: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataNote = BangunDatarEntity(
                    type = type,
                    value1 = value1,
                    value2 = value2,
                    area = area,
                    circumference = circumference
                )
                db.insertNote(dataNote)
            }
        }
    }
}