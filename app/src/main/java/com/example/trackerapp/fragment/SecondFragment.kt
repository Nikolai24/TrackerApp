package com.example.trackerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.trackerapp.Habit
import com.example.trackerapp.R
import com.example.trackerapp.databinding.FragmentSecondBinding

class SecondFragment: Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var name: String = HABIT
    private var description: String = DESCRIPTION
    private var priority: String = HIGH
    private var type: String = GOOD_HABIT
    private var quantity: Int = 0
    private var periodicity: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var item: Habit = arguments?.getSerializable(HABIT) as Habit
        var position: Int = arguments?.getInt(POSITION) as Int
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.priorities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
        binding.radioButtonGood.setOnClickListener {
            type = GOOD_HABIT
        }
        binding.radioButtonBad.setOnClickListener {
            type = BAD_HABIT
        }
        val saveButton: Button = view.findViewById(R.id.save_habit)
        val cancelButton: Button = view.findViewById(R.id.cancel)
        if (position == -1){
            item = Habit(name, description, priority, type, quantity, periodicity)
            saveButton.setOnClickListener {
                sendResult(item, -1, binding.spinner)
            }
        }
        if (position > -1){
            binding.nameHabit.setText(item.name)
            binding.description.setText(item.description)
            binding.quantity.setText(item.quantity!!.toString())
            binding.periodicity.setText(item.periodicity!!.toString())
            if (item.type == GOOD_HABIT){
                binding.radioGroup.check(R.id.radioButtonGood)
                type = GOOD_HABIT
            } else {
                binding.radioGroup.check(R.id.radioButtonBad)
                type = BAD_HABIT
            }
            if (item.priority == MEDIUM){
                binding.spinner.setSelection(1)
            }
            if (item.priority == LOW){
                binding.spinner.setSelection(2)
            }
            saveButton.setOnClickListener {
                sendResult(item, position, binding.spinner)
            }
        }
        cancelButton.setOnClickListener {
            sendResult(item, -2, binding.spinner)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sendResult(habit: Habit, position: Int, spinner: Spinner) {
        priority = spinner.selectedItem.toString()
        habit.priority = priority
        var text = binding.nameHabit.text.toString()
        if (text.isNotEmpty()){
            habit.name = text
        }
        text = binding.description.text.toString()
        if (text.isNotEmpty()){
            habit.description = text
        }
        text = binding.quantity.text.toString()
        if (text.isNotEmpty()){
            habit.quantity = text.toInt()
        }
        text = binding.periodicity.text.toString()
        if (text.isNotEmpty()){
            habit.periodicity = text.toInt()
        }
        habit.type = type
        setFragmentResult(REQUEST_KEY, bundleOf(HABIT to habit, POSITION to position))
        activity?.onBackPressed()
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Habit, position: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putSerializable(HABIT, item)
            args.putInt(POSITION, position)
            fragment.arguments = args
            return fragment
        }
        private const val HABIT = "Habit"
        private const val DESCRIPTION = ""
        private const val GOOD_HABIT = "Good habit"
        private const val BAD_HABIT = "Bad habit"
        private const val HIGH = "High"
        private const val MEDIUM = "Medium"
        private const val LOW = "Low"
        private const val POSITION = "Position"
        private const val REQUEST_KEY = "Request_key"
    }
}