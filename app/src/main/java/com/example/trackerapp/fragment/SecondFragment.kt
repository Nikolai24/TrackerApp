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

class SecondFragment: Fragment() {
    private var name: String = HABIT
    private var description: String = DESCRIPTION
    private var priority: String = HIGH
    private var type: String = GOOD_HABIT
    private var quantity: Int = 0
    private var periodicity: Int = 0
    private lateinit var nameHabit: EditText
    private lateinit var descriptionHabit: EditText
    private lateinit var quantityHabit: EditText
    private lateinit var periodicityHabit: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonGood: RadioButton
    private lateinit var radioButtonBad: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var item: Habit = arguments?.getSerializable(HABIT) as Habit
        var position: Int = arguments?.getInt(POSITION) as Int

        val spinner: Spinner = view.findViewById(R.id.priorities_spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.priorities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        nameHabit = view.findViewById(R.id.name)
        descriptionHabit = view.findViewById(R.id.description)
        quantityHabit = view.findViewById(R.id.quantity)
        periodicityHabit = view.findViewById(R.id.periodicity)
        radioGroup = view.findViewById(R.id.radios)
        radioButtonGood = view.findViewById(R.id.good_habit)
        radioButtonBad = view.findViewById(R.id.bad_habit)
        radioButtonGood.setOnClickListener {
            type = GOOD_HABIT
        }
        radioButtonBad.setOnClickListener {
            type = BAD_HABIT
        }
        val saveButton: Button = view.findViewById(R.id.save_habit)
        val cancelButton: Button = view.findViewById(R.id.cancel)
        if (position == -1){
            item = Habit(name, description, priority, type, quantity, periodicity)
            saveButton.setOnClickListener {
                sendResult(item, -1, spinner)
            }
        }
        if (position > -1){
            nameHabit.setText(item.name)
            descriptionHabit.setText(item.description)
            quantityHabit.setText(item.quantity!!.toString())
            periodicityHabit.setText(item.periodicity!!.toString())
            if (item.type == GOOD_HABIT){
                radioGroup.check(R.id.good_habit)
                type = GOOD_HABIT
            } else {
                radioGroup.check(R.id.bad_habit)
                type = BAD_HABIT
            }
            if (item.priority == MEDIUM){
                spinner.setSelection(1)
            }
            if (item.priority == LOW){
                spinner.setSelection(2)
            }
            saveButton.setOnClickListener {
                sendResult(item, position, spinner)
            }
        }
        cancelButton.setOnClickListener {
            sendResult(item, -2, spinner)
        }
    }

    private fun sendResult(habit: Habit, position: Int, spinner: Spinner) {
        priority = spinner.selectedItem.toString()
        habit.priority = priority
        var text = nameHabit.text.toString()
        if (text.isNotEmpty()){
            habit.name = text
        }
        text = descriptionHabit.text.toString()
        if (text.isNotEmpty()){
            habit.description = text
        }
        text = quantityHabit.text.toString()
        if (text.isNotEmpty()){
            habit.quantity = text.toInt()
        }
        text = periodicityHabit.text.toString()
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
        private const val BAD_HABIT = "Bad habit"
        private const val GOOD_HABIT = "Good habit"
        private const val HIGH = "High"
        private const val MEDIUM = "Medium"
        private const val LOW = "Low"
        private const val POSITION = "Position"
        private const val REQUEST_KEY = "Request_key"
    }
}