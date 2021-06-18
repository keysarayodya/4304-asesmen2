package org.d3if0044.bangundatar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0044.bangundatar.data.local.BangunDatarDao
import org.d3if0044.bangundatar.data.local.BangunDatarEntity

class HistoriViewModel(private val local: BangunDatarDao) : ViewModel() {
    private val _noteCalculation = local.getNote()

    val noteCalculation: LiveData<List<BangunDatarEntity>>
        get() = _noteCalculation

    fun addNote(noteData : BangunDatarEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                local.insertNote(noteData)
            }
        }
    }

    fun updateNote(note: BangunDatarEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            local.updateNote(note)
        }
    }

    fun deleteNote(note: BangunDatarEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            local.deleteNote(note)
        }
    }

    fun clearData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            local.clearData()
        }
    }
}