package com.example.trackerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.trackerapp.Habit
import com.example.trackerapp.R
import com.example.trackerapp.adapter.ViewAdapter
import com.example.trackerapp.databinding.FragmentFirstBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewAdapter: ViewAdapter
    private var habits: ArrayList<Habit> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                habits = getSerializable(LIST) as ArrayList<Habit>
            }
        }
        setFragmentResultListener(REQUEST_KEY) { requestKey, bundle ->
            val pos = bundle.getInt(POSITION)
            if (pos == -1 ) {
                val item = bundle.getSerializable(HABIT) as Habit
                habits.add(item)
            }
            if (pos > 1 ) {
                val item = bundle.getSerializable(HABIT) as Habit
                habits[pos] = item
            }
            if (pos == -2) {
                run {
                    val toast = Toast.makeText(requireContext(), TOAST, Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewAdapter = ViewAdapter(this, habits)
        binding.viewPager.adapter = viewAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = GOOD_HABITS
                else -> tab.text = BAD_HABITS
            }
        }.attach()
        binding.fab.setOnClickListener {
            val habit = Habit()
            val position = -1
            commitTransaction(habit, position)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun commitTransaction(item: Habit, position: Int) {
        val secondFragment: Fragment = SecondFragment.newInstance(item, position)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, secondFragment)
            .addToBackStack(null).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(LIST, habits)
        super.onSaveInstanceState(outState)
    }

    companion object {
        @JvmStatic
        fun newInstance(): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
        private const val LIST = "List"
        private const val HABIT = "Habit"
        private const val POSITION = "Position"
        private const val REQUEST_KEY = "Request_key"
        private const val TOAST = "The list of habits has not changed"
        private const val GOOD_HABITS = "Good habits"
        private const val BAD_HABITS = "Bad habits"
    }
}