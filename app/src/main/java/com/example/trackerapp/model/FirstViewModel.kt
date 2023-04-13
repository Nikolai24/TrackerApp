package com.example.trackerapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trackerapp.Habit
import com.example.trackerapp.Singleton

class FirstViewModel(private val model: Singleton) {

    private val mutableGoodList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val goodList: LiveData<MutableList<Habit>?> = mutableGoodList

    private val mutableBadList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val badList: LiveData<MutableList<Habit>?> = mutableBadList

    init {
        getList()
    }
    private fun getList() {
        mutableGoodList.value = Singleton.getGoodList()
        mutableBadList.value = Singleton.getBadList()
    }

    fun sort(){
        Singleton.sort()
    }

    fun search(s: String){
        Singleton.search(s)
    }
}