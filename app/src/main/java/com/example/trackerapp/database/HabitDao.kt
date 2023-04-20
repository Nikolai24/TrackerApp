package com.example.trackerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trackerapp.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit_table")
    fun getAll(): LiveData<List<Habit>>

    @Insert
    fun insertAll(vararg habit: Habit)

    @Query("SELECT * FROM habit_table WHERE id LIKE :id LIMIT 1")
    fun findByID(id: Int): Habit

    @Update
    fun update(habit: Habit)
}