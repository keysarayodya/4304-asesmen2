package org.d3if0044.bangundatar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BangunDatarEntity::class], version = 1, exportSchema = false)
abstract class BangunDatarDatabase : RoomDatabase() {
    abstract val dao: BangunDatarDao

    companion object {
        @Volatile
        private var INSTANCE: BangunDatarDatabase? = null

        fun getInstance(context: Context): BangunDatarDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BangunDatarDatabase::class.java,
                        "note_calculation_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}