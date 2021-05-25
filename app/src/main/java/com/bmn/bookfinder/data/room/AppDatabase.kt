package com.bmn.bookfinder.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract val bookDao: BookDao

    companion object {
        private const val DB_NAME = "bookFinder.db"
        private lateinit var INSTANCE: AppDatabase

        @JvmStatic
        fun getDatabase(context: Context?): AppDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context!!, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}