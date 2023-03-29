package com.example.trackerapp.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trackerapp.Habit
import com.example.trackerapp.fragment.NewFragment

class ViewAdapter(fragment: Fragment, newList: ArrayList<Habit>) : FragmentStateAdapter(fragment) {
    val list = newList

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = NewFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
            putSerializable(ARRAY_LIST, list)
        }
        return fragment
    }
    companion object {
        private const val ARG_OBJECT = "object"
        private const val ARRAY_LIST = "array list"
    }
}