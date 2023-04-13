package com.example.trackerapp.model

import com.example.trackerapp.Habit
import com.example.trackerapp.Singleton

class SecondViewModel(private val model: Singleton) {
    init {
    }
    var pos = -1
    var habit = Habit("Habit", "", "High", "Good habit", 0, 0)
    var oldType = "Good habit"

    fun updateList() {
        if (pos == -1) {
            Singleton.addHabit(habit)
        }
        if (pos > -1) {
            Singleton.changeHabit(pos, habit, oldType)
        }
    }

    fun name(s: String){
        habit.name = s
    }

    fun description(s: String){
        habit.description = s
    }

    fun quantity(s: String){
        habit.quantity = s.toInt()
    }

    fun periodicity(s: String){
        habit.periodicity = s.toInt()
    }

    fun priority(s: String){
        habit.priority = s
    }

    fun type(s: String){
        habit.type = s
    }

    fun position(p: Int){
        pos = p
    }

    fun oldType(s: String){
        oldType = s
    }

    fun id(id: Int){
        habit.id = id
    }

}