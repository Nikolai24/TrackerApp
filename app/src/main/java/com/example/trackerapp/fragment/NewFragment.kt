package com.example.trackerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trackerapp.Habit
import com.example.trackerapp.R
import com.example.trackerapp.adapter.DataAdapter
import com.example.trackerapp.databinding.FragmentNewBinding

class NewFragment : Fragment() {
    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!
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
        _binding =  FragmentNewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            num = getInt(ARG_OBJECT)
            list = getSerializable(ARRAY_LIST) as ArrayList<Habit>
        }
        var newList: ArrayList<Habit> = arrayListOf()
        if (num == 0){
            for (i in list){
                if (i.type == GOOD_HABIT){
                    newList.add(i)
                }
            }

        } else {
            for (i in list){
                if (i.type == BAD_HABIT){
                    newList.add(i)
                }
            }
        }
        dataAdapter = DataAdapter(newList, listener)
        binding.recyclerView.adapter = dataAdapter
        val lManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.apply { layoutManager = lManager }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun commitTransaction(item: Habit, position: Int) {
        val secondFragment: Fragment = SecondFragment.newInstance(item, position)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, secondFragment)
            ?.addToBackStack(null)?.commit()
    }
    companion object {
        private const val ARG_OBJECT = "Object"
        private const val ARRAY_LIST = "Array list"
        private const val GOOD_HABIT = "Good habit"
        private const val BAD_HABIT = "Bad habit"
    }
}