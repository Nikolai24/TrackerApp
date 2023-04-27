package com.example.trackerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trackerapp.Habit
import com.example.trackerapp.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SecondViewModel(private val model: HabitRepository, var id: Int) {

    private var mutableHabit: MutableLiveData<Habit?> = MutableLiveData()
    var habit: LiveData<Habit?> = mutableHabit
    var position = -1
    var newHabit = Habit("Habit", "", "High", "Good habit", 0, 0, 0)
    var oldType = "Good habit"

    init {
        getHabit(id)
    }

    private fun getHabit(id: Int){
        mutableHabit.value = model.getHabit(id)
    }

    fun updateList() {
//        val job: Job = GlobalScope.launch(Dispatchers.IO) {
//            if (id == -1) {
//                model.addHabit(newHabit)
//            }
//            if (id > -1) {
//                model.changeHabit(id, newHabit)
//            }
//        }
//        job.start()

        if (id == -1) {
            model.addHabit(newHabit)
        }
        if (id > -1) {
            model.changeHabit(id, newHabit)
        }
    }

    fun name(s: String){
        newHabit.name = s
    }

    fun description(s: String){
        newHabit.description = s
    }

    fun quantity(s: String){
        if (s == ""){
            newHabit.quantity = 0
        } else {
            newHabit.quantity = s.toInt()
        }
    }

    fun periodicity(s: String){
        if (s == ""){
            newHabit.periodicity = 0
        } else {
            newHabit.periodicity = s.toInt()
        }
    }

    fun priority(s: String){
        newHabit.priority = s
    }

    fun type(s: String){
        newHabit.type = s
    }

    fun position(p: Int){
        position = p
    }

    fun oldType(s: String){
        oldType = s
    }

//    fun id(id: Int){
//        newHabit.id = id
//    }

}