package com.example.trackerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trackerapp.Habit
import com.example.trackerapp.R
import com.example.trackerapp.adapter.DataAdapter

class NewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataAdapter: DataAdapter
    private var list: ArrayList<Habit> = arrayListOf()
    private var num = 0

    private val listener: DataAdapter.OnItemClickListener = object: DataAdapter.OnItemClickListener{
        override fun onItemClick(item: Habit, position: Int) {
            commitTransaction(item, position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            num = getInt(ARG_OBJECT)
            list = getSerializable(ARRAY_LIST) as ArrayList<Habit>
        }
        var newList: ArrayList<Habit> = arrayListOf()
        if (num == 0){
            for (i in list){
                if (i.type == "Good habit"){
                    newList.add(i)
                }
            }

        } else {
            for (i in list){
                if (i.type == "Bad habit"){
                    newList.add(i)
                }
            }
        }
        dataAdapter = DataAdapter(newList, listener)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = dataAdapter
        recyclerView = view.findViewById(R.id.recycler_view)
        val lManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.apply { layoutManager = lManager }
//        dataAdapter.setHabits(habits)
    }

    fun commitTransaction(item: Habit, position: Int) {
        val secondFragment: Fragment = SecondFragment.newInstance(item, position)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, secondFragment)
            ?.addToBackStack(null)?.commit()
//        parentFragment?.parentFragmentManager?.beginTransaction()
//            ?.replace(R.id.fragment_container, secondFragment)
//            ?.addToBackStack(null)?.commit()
    }
    companion object {
        private const val ARG_OBJECT = "object"
        private const val ARRAY_LIST = "array list"
    }
}