package com.example.trackerapp

import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_table")
data class Habit(
    @ColumnInfo var name: String? = null,
    @ColumnInfo var description: String? = null,
    @ColumnInfo var priority: String? = null,
    @ColumnInfo var type: String? = null,
    @ColumnInfo var quantity: Int? = null,
    @ColumnInfo var periodicity: Int? = null,
    @ColumnInfo var color: Int? = null,
) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}
