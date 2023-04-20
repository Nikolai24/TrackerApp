package com.example.trackerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trackerapp.Habit
import com.example.trackerapp.HabitRepository

class FirstViewModel(private val model: HabitRepository) {

    private val mutableGoodList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val goodList: LiveData<MutableList<Habit>?> = mutableGoodList

    private val mutableBadList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val badList: LiveData<MutableList<Habit>?> = mutableBadList

    init {
        getList()
    }
    private fun getList() {
        var all = model.getHabitsList()
        var good = mutableListOf<Habit>()
        var bad = mutableListOf<Habit>()
        for (i in all) {
            if (i.type == "Good habit") {
                good.add(i)
            } else {
                bad.add(i)
            }
        }
        mutableGoodList.value = good
        mutableBadList.value = bad
    }

    fun sort(){
        model.sort()
    }

    fun search(s: String){
        model.search(s)
    }
}