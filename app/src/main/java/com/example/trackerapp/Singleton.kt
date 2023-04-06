package com.example.trackerapp

object Singleton {
    private var habits: MutableList<Habit> = mutableListOf()
    private var goodHabits: MutableList<Habit> = mutableListOf()
    private var badHabits: MutableList<Habit> = mutableListOf()

    fun getGoodList(): MutableList<Habit> {
        return goodHabits
    }

    fun getBadList(): MutableList<Habit> {
        return badHabits
    }

    fun addHabit(habit: Habit) {
        habit.id = habits.size
        habits.add(habit)
        if (habit.type == "Good habit") {
            goodHabits.add(habit)
        }
        if (habit.type == "Bad habit") {
            badHabits.add(habit)
        }
    }

    fun changeHabit(pos:Int, newHabit: Habit, oldType: String) {
        var id = newHabit.id
//        var oldType = habits[id!!].type
        var newType = newHabit.type
        if (newType == "Good habit" && oldType == "Good habit") {
            goodHabits[pos] = newHabit
        }
        if (newType == "Good habit" && oldType == "Bad habit") {
            badHabits.removeAt(pos)
            goodHabits.add(newHabit)
        }
        if (newType == "Bad habit" && oldType == "Bad habit") {
            badHabits[pos] = newHabit
        }
        if (newType == "Bad habit" && oldType == "Good habit") {
            goodHabits.removeAt(pos)
            badHabits.add(newHabit)
        }
        habits[id!!] = newHabit
    }

    fun sort(){
        goodHabits = goodHabits.sortedBy {it.quantity}.toMutableList()
        badHabits = badHabits.sortedBy {it.quantity}.toMutableList()
    }
    fun search(s: String){
        var newList: MutableList<Habit> = mutableListOf()
        for (i in goodHabits){
            if (i.name == s) {
                newList.add(i)
            }
        }
        goodHabits = newList
        newList.clear()
        for (i in badHabits){
            if (i.name == s) {
                newList.add(i)
            }
        }
        badHabits = newList
        newList.clear()
    }

//    companion object {
//        private const val GOOD_HABIT = "Good habit"
//        private const val BAD_HABIT = "Bad habit"
//    }
}