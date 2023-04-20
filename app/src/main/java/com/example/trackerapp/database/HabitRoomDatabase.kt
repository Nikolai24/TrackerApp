package com.example.trackerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackerapp.Habit

@Database(entities = [Habit::class], version = 1)
abstract class HabitRoomDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}