package com.example.trackerapp

class SecondViewModel(private val model: Singleton, private val pos: Int, private val habit: Habit) {
    init {
        updateList()
    }
    private fun updateList() {
        if (pos == -1) {
            model.addHabit(habit)
        }
        if (pos > -1) {
            model.changeHabit(pos, habit)
        }
    }
}