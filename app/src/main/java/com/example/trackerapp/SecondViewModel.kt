package com.example.trackerapp

class SecondViewModel(private val model: Singleton, private val pos: Int, private val habit: Habit, private val oldType: String) {
    init {
        updateList()
    }
    private fun updateList() {
        if (pos == -1) {
            model.addHabit(habit)
        } else {
            model.changeHabit(pos, habit, oldType)
        }
    }
}