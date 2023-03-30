package com.example.trackerapp

object Singleton {
    private var habits: MutableList<Habit> = mutableListOf()
//    var habit = Habit("Habit", "123", "High", "Good habit", 3, 4)
//    var habits: MutableList<Habit> = mutableListOf(habit)

    fun getList(): MutableList<Habit> {
        return habits
    }

    fun addHabit(habit: Habit) {
        habits.add(habit)
    }

    fun changeHabit(pos:Int, habit: Habit) {
        habits[pos] = habit
    }
}