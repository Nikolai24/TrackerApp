package com.example.trackerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trackerapp.Habit

@Database(entities = [Habit::class], version = 1)
abstract class HabitRoomDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao


    companion object {

        private var INSTANCE: HabitRoomDatabase? = null

        fun getDatabase(context: Context): HabitRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitRoomDatabase::class.java, "HabitReader"
                ).allowMainThreadQueries().build()
            }
        }
    }
}