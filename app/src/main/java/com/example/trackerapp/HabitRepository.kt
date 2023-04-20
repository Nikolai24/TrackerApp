package com.example.trackerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.trackerapp.database.HabitDao
import com.example.trackerapp.database.HabitRoomDatabase

class HabitRepository(private val habitDao: HabitDao) {
    private var habits: LiveData<List<Habit>> = MutableLiveData()
    var newHabit = Habit("New habit", "", "High", "Good habit", 0, 0, 0, 0)

    private val db = Room.databaseBuilder(
        applicationContext,
        HabitRoomDatabase#:class.java, "HabitReader"
    ).allowMainThreadQueries().build()

    fun getHabit(id: Int): Habit {
        newHabit = db.habitDao.findByID(id)
        return newHabit
//        for (i in habits){
//            if (i.id == id){
//                println(i)
//                return i
//            }
//        }
//        return newHabit
    }

    fun getHabitsList(): LiveData<List<Habit>> {
        habits = db.habitDao.getAll()
        return habits
    }

    fun addHabit(habit: Habit) {
        db.habitDao.incertAll(habit)
//        habit.id = habits.size
//        habits.add(habit)
    }

    fun changeHabit(pos:Int, newHabit: Habit) {
        db.habitDao.update(newHabit)
//        var id = newHabit.id
//        habits[id!!] = newHabit
    }

    fun sort(){
//       habits = habits.sortedBy {it.quantity}.toMutableList()
    }
    fun search(s: String){
//        var newList: MutableList<Habit> = mutableListOf()
//        for (i in goodHabits){
//            if (i.name == s) {
//                newList.add(i)
//            }
//        }
//        goodHabits = newList
//        newList.clear()
//        for (i in badHabits){
//            if (i.name == s) {
//                newList.add(i)
//            }
//        }
//        badHabits = newList
//        newList.clear()
    }

//    companion object {
//        private const val GOOD_HABIT = "Good habit"
//        private const val BAD_HABIT = "Bad habit"
//    }
}