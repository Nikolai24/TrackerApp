package com.example.trackerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trackerapp.fragment.RecyclerViewFragment

class FirstViewModel(private val model: Singleton) {

    private val mutableGoodList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val goodList: LiveData<MutableList<Habit>?> = mutableGoodList

    private val mutableBadList: MutableLiveData<MutableList<Habit>?> = MutableLiveData()
    val badList: LiveData<MutableList<Habit>?> = mutableBadList

    init {
        getList()
    }
    private fun getList() {
        mutableGoodList.value = model.getGoodList()
        mutableBadList.value = model.getBadList()
    }

    fun sort(){
        model.sort()
    }

    fun search(s: String){
        model.search(s)
    }
}