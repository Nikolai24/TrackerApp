package com.example.trackerapp.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trackerapp.Habit
import com.example.trackerapp.fragment.RecyclerViewFragment

class ViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = RecyclerViewFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
        }
        return fragment
    }
    companion object {
        private const val ARG_OBJECT = "Object"
    }
}