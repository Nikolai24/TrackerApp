package com.example.trackerapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.trackerapp.*
import com.example.trackerapp.HabitRepository
import com.example.trackerapp.database.HabitRoomDatabase
import com.example.trackerapp.databinding.FragmentEditBinding
import com.example.trackerapp.viewmodel.SecondViewModel

class EditFragment: Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SecondViewModel
    private var name: String = HABIT
    private var description: String = DESCRIPTION
    private var priority: String = HIGH
    private var type: String = GOOD_HABIT
    private var quantity: Int = 0
    private var periodicity: Int = 0
    var oldType = GOOD_HABIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SecondViewModel(HabitRepository(HabitRoomDatabase.getDatabase(requireContext()).habitDao()), arguments?.getInt(ID) as Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentEditBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position: Int = arguments?.getInt(POSITION) as Int
        println("EDIT FRAGMENT")
        var item = Habit()
//        viewModel.habit.observe(viewLifecycleOwner, Observer { habit ->
//            item = habit!!
//            println(item)
//        })
        val db = HabitRoomDatabase.getDatabase(requireContext()).habitDao()
        item = db.findByID( arguments?.getInt(ID) as Int)

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
            viewModel.type(type)
        }
        binding.radioButtonBad.setOnClickListener {
            type = BAD_HABIT
            viewModel.type(type)
        }
        val saveButton: Button = view.findViewById(R.id.save_habit)
        val cancelButton: Button = view.findViewById(R.id.cancel)
        if (position == -1){
            item = Habit(name, description, priority, type, quantity, periodicity)
            saveButton.setOnClickListener {
                viewModel.priority(binding.spinner.selectedItem.toString())
                viewModel.updateList()
//                sendResult(item, -1, binding.spinner)
                activity?.onBackPressed()
            }
        }
        if (position > -1){
            binding.nameHabit.setText(item.name)
            binding.description.setText(item.description)
            binding.quantity.setText(item.quantity!!.toString())
            viewModel.quantity(item.quantity!!.toString())
            binding.periodicity.setText(item.periodicity!!.toString())
            viewModel.periodicity(item.periodicity!!.toString())
            if (item.type == GOOD_HABIT){
                binding.radioGroup.check(R.id.radioButtonGood)
                type = GOOD_HABIT
                oldType = GOOD_HABIT
            } else {
                binding.radioGroup.check(R.id.radioButtonBad)
                type = BAD_HABIT
                oldType = BAD_HABIT
            }
            var priority = ""
            if (item.priority == HIGH){
                binding.spinner.setSelection(0)
                priority = HIGH
            }
            if (item.priority == MEDIUM){
                binding.spinner.setSelection(1)
                priority = MEDIUM
            }
            if (item.priority == LOW){
                binding.spinner.setSelection(2)
                priority = LOW
            }
            saveButton.setOnClickListener {
                viewModel.priority(binding.spinner.selectedItem.toString())
                viewModel.updateList()
//                sendResult(item, position, binding.spinner)
                activity?.onBackPressed()
            }
            viewModel.name(item.name!!)
            viewModel.priority(priority)
            viewModel.type(type)
//            viewModel.id(item.id!!)
        }
        viewModel.position(position)
        viewModel.oldType(oldType)
        binding.nameHabit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.name(s.toString())
            }
        })
        binding.description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.description(s.toString())
            }
        })
        binding.quantity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.quantity(s.toString())
            }
        })
        binding.periodicity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.periodicity(s.toString())
            }
        })
//        cancelButton.setOnClickListener {
//            sendResult(item, -2, binding.spinner)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, position: Int): EditFragment {
            val fragment = EditFragment()
            val args = Bundle()
            args.putInt(ID, id)
            args.putInt(POSITION, position)
            fragment.arguments = args
            return fragment
        }
        private const val HABIT = "Habit"
        private const val ID = "ID"
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