package org.d3if0044.bangundatar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_calculation")
data class BangunDatarEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var type: String,
    var value1: String,
    var value2: String,
    var area: String,
    var circumference: String
)