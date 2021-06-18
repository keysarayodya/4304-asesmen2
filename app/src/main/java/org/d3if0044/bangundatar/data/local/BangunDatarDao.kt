package org.d3if0044.bangundatar.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BangunDatarDao {
    @Insert
    fun insertNote(bangunDatar: BangunDatarEntity)

    @Query("SELECT * FROM note_calculation ORDER BY id DESC")
    fun getNote(): LiveData<List <BangunDatarEntity>>

    @Delete
    fun deleteNote(bangunDatar: BangunDatarEntity)

    @Update
    fun updateNote(bangunDatar: BangunDatarEntity)

    @Query("DELETE FROM note_calculation")
    fun clearData()
}