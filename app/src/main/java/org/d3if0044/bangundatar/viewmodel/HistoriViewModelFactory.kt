package org.d3if0044.bangundatar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0044.bangundatar.data.local.BangunDatarDao

class HistoriViewModelFactory (
    private val local: BangunDatarDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoriViewModel::class.java)) {
            return HistoriViewModel(local) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
