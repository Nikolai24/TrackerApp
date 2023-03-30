package com.example.trackerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FirstViewModel(private val model: Singleton) {
    private val mutableHabitList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val habitList: LiveData<MutableList<Habit>?> = mutableHabitList
    init {
        getList()
    }
    private fun getList() {
        mutableHabitList.value = model.getList()
    }
}